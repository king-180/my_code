package com.wx.demo.seckill.service.impl;

import com.wx.demo.seckill.feign.CouponFeignService;
import com.wx.demo.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author wangxing
 * @date 2021/7/19 0:35
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private CouponFeignService couponFeignService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String SESSIONS_CACHE_PREFIX = "seckill:sessions:";

    private static final String SKUS_CACHE_PREFIX = "seckill:skus:";

    @Override
    public void uploadSeckillSkuLastest3Days() {
        // 扫描需要参加秒杀的商品

        // 缓存到redis
        String key = SESSIONS_CACHE_PREFIX + "startTime" + "_" + "endTime";
        List<String> skuIds = Collections.singletonList("1001,1002,1003");
        // 缓存活动信息
        stringRedisTemplate.opsForList().leftPushAll(key, skuIds);
        // 缓存商品信息
        BoundHashOperations<String, Object, Object> ops = stringRedisTemplate.boundHashOps(SKUS_CACHE_PREFIX);
        ops.put("1001", "skuInfoVoJsonStr");

    }
}
