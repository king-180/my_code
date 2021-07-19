package com.wx.demo.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.wx.demo.seckill.feign.CouponFeignService;
import com.wx.demo.seckill.interceptor.LoginInterceptor;
import com.wx.demo.seckill.mq.SeckillOrderTO;
import com.wx.demo.seckill.service.SeckillService;
import com.wx.demo.seckill.to.SeckillSkuRedisTO;
import com.wx.demo.seckill.vo.MemberRespVO;
import com.wx.demo.seckill.vo.SeckillSkuInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author wangxing
 * @date 2021/7/19 0:35
 */
@Slf4j
@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private CouponFeignService couponFeignService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String SESSIONS_CACHE_PREFIX = "seckill:sessions:";

    private static final String SKUS_CACHE_PREFIX = "seckill:skus:";

    /**
     * 后面拼接商品随机码
     */
    private static final String SKUS_STOCK_SEMAPHORE_PREFIX = "seckill:stock:";

    @Override
    public void uploadSeckillSkuLastest3Days() {
        // 扫描需要参加秒杀的商品
        List<SeckillSkuInfoVO> seckillSkuInfos = couponFeignService.getSeckillSkuInfo();

        // 秒杀场次id加商品id
        List<String> skuIds = seckillSkuInfos.stream().map(seckillSkuInfoVO -> seckillSkuInfoVO.getId().toString() + "_" + seckillSkuInfoVO.getSkuId().toString()).collect(Collectors.toList());
        // 缓存到redis
        String key = SESSIONS_CACHE_PREFIX + "20210719000000" + "_" + "20210721235959";
        // 缓存活动信息
        Boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey != null && !hasKey) {
            redisTemplate.opsForList().leftPushAll(key, skuIds);
        }

        BoundHashOperations<String, String, String> ops = redisTemplate.boundHashOps(SKUS_CACHE_PREFIX);

        seckillSkuInfos.forEach(seckillSkuInfoVO -> {
            Boolean hasOpsKey = ops.hasKey(seckillSkuInfoVO.getId().toString() + "_" + seckillSkuInfoVO.getSkuId().toString());
            if (hasOpsKey != null && !hasOpsKey) {
                // 秒杀链接携带请求随机码 http:xxx.com/seckill?skuId=1001&token=twikiasdjxskdcscsfac
                String token = UUID.randomUUID().toString().replace("-", "");

                SeckillSkuRedisTO skuRedisTO = new SeckillSkuRedisTO();

                skuRedisTO.setStartTime(seckillSkuInfoVO.getStartTime().getTime());
                skuRedisTO.setEndTime(seckillSkuInfoVO.getEndTime().getTime());
                skuRedisTO.setRandomCodeToken(token);

                // 缓存商品信息
                ops.put(seckillSkuInfoVO.getId().toString() + "_" + seckillSkuInfoVO.getSkuId().toString(), JSON.toJSONString(skuRedisTO));

                // 商品可以秒杀的数量作为分布式的信号量许可证
                RSemaphore semaphore = redissonClient.getSemaphore(SKUS_STOCK_SEMAPHORE_PREFIX + token);
                semaphore.trySetPermits(seckillSkuInfoVO.getSeckillCount());
            }

        });

    }

    @Override
    public List<SeckillSkuRedisTO> getCurrentSeckillSkus() {
        // 确定当前时间属于那个秒杀场次
        long time = System.currentTimeMillis();
        Set<String> keys = redisTemplate.keys(SESSIONS_CACHE_PREFIX + "*");
        // seckill:sessions:1582225044000_1586555550000
        for (String key : keys) {
            String startTime_endTime = key.replace(SESSIONS_CACHE_PREFIX, "");
            String[] times = startTime_endTime.split("_");
            long startTime = Long.parseLong(times[0]);
            long endTime = Long.parseLong(times[1]);
            if (time >= startTime && time <= endTime) {
                // 获取当前这个秒杀场次的所有商品信息
                List<String> range = redisTemplate.opsForList().range(key, -100, 100);
                BoundHashOperations<String, String, String> hashOps = redisTemplate.boundHashOps(SKUS_CACHE_PREFIX);
                List<String> list = hashOps.multiGet(range);
                if (!CollectionUtils.isEmpty(list)) {
                    return list.stream().map(item -> {
                        SeckillSkuRedisTO redisTO = JSON.parseObject((String) item, SeckillSkuRedisTO.class);
                        // redisTO.setRandomCodeToken(null); 当前秒杀已经开始，需要返回随机码,不需要屏蔽
                        return redisTO;
                    }).collect(Collectors.toList());
                }
                break;
            }
        }

        return null;
    }

    @Override
    public SeckillSkuRedisTO getSkuSeckillInfo(Long skuId) {
        BoundHashOperations<String, String, String> hashOps = redisTemplate.boundHashOps(SKUS_CACHE_PREFIX);
        Set<String> keys = hashOps.keys();
        String regx = "\\d_" + skuId;
        if (!CollectionUtils.isEmpty(keys)) {
            for (String key : keys) {
                // 6_4, 5_1
                if (Pattern.matches(regx, key)) {
                    String json = hashOps.get(key);
                    SeckillSkuRedisTO seckillSkuRedisTO = JSON.parseObject(json, SeckillSkuRedisTO.class);
                    long now = System.currentTimeMillis();
                    if (!(now >= seckillSkuRedisTO.getStartTime() && now <= seckillSkuRedisTO.getEndTime())) {
                        // 不在活动时间内，隐藏随机码
                        seckillSkuRedisTO.setRandomCodeToken(null);
                    }
                    return seckillSkuRedisTO;
                }
            }
        }
        return null;
    }

    @Override
    public String kill(String killId, String key, Integer num) {
        // 获取登陆用户信息
        MemberRespVO respVO = LoginInterceptor.loginUser.get();
        // 获取当前商品详细信息
        BoundHashOperations<String, String, String> hashOps = redisTemplate.boundHashOps(SKUS_CACHE_PREFIX);
        String json = hashOps.get(killId);
        if (StringUtils.isEmpty(json)) {
            return null;
        } else {
            SeckillSkuRedisTO redisTO = JSON.parseObject(json, SeckillSkuRedisTO.class);
            // 校验合法性
            long current = System.currentTimeMillis();
            // 时间校验
            if (current >= redisTO.getStartTime() && current <= redisTO.getEndTime()) {
                // 随机码校验 和 商品id
                String randomCode = redisTO.getRandomCodeToken();
                String skuId = redisTO.getPromotionSessionId() + "_" + redisTO.getSkuId();
                if (key.equals(randomCode) && killId.equals(skuId)) {
                    // 购物数量是否合法
                    if (num <= redisTO.getSeckillLimit()) {
                        // 验证该用户是否已经购买过，幂等性，如果秒杀成功，去redis占位 userId_sessionId_skuId
                        String redisKey = respVO.getId() + "_" + skuId;
                        Long ttl = redisTO.getEndTime() - current;
                        Boolean absent = redisTemplate.opsForValue().setIfAbsent(redisKey, String.valueOf(num), ttl, TimeUnit.MILLISECONDS);
                        if (absent != null && absent) {
                            // 占位成功，说明还没买过
                            RSemaphore semaphore = redissonClient.getSemaphore(SKUS_STOCK_SEMAPHORE_PREFIX + randomCode);
                            try {
                                boolean acquire = semaphore.tryAcquire(num, 200, TimeUnit.MILLISECONDS);

                                if (acquire) {
                                    // 秒杀成功，快速下单，发送 MQ消息
                                    String orderSn = IdWorker.getTimeId();
                                    log.info("秒杀订单号：{}", orderSn);
                                    // 给 MQ 发消息
                                    SeckillOrderTO order = new SeckillOrderTO();
                                    order.setOrderSn(orderSn);
                                    order.setNum(num);
                                    order.setMemberId(respVO.getId());
                                    order.setPromotionSessionId(redisTO.getPromotionSessionId());
                                    order.setSkuId(redisTO.getSkuId());
                                    order.setSeckillPrice(redisTO.getSecskillPrice());
                                    rabbitTemplate.convertAndSend("order-event-exchange", "order.seckill.order", order);
                                    log.info("秒杀总共耗时：{} ms", (System.currentTimeMillis() - current));
                                    return orderSn;
                                }

                            } catch (InterruptedException e) {
                                log.error("秒杀失败...,异常信息：{}", e.toString());
                                e.printStackTrace();
                            }

                        } else {
                            return null;
                        }
                    }
                }

            } else {
                return null;
            }
        }
        return null;
    }

}
