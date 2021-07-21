package com.wx.demo.seckill.scheduled;

import com.wx.demo.seckill.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author wangxing
 * @date 2021/7/19 0:31
 */
@Slf4j
@Service
public class SeckillSkuScheduled {

    @Autowired
    private SeckillService seckillService;

    @Autowired
    private RedissonClient redissonClient;

    private static final String UPLOAD_LOCK = "seckill:upload:lock";

    /**
     * 秒杀上架：每天晚上3：00 上架最近三天需要秒杀的商品
     */
    @Scheduled(cron = "* * 3 * * ?")
    public void uploadSeckillSkuLastest3Days() {
        // 幂等性问题处理，不能重复上架，分布式锁
        RLock lock = redissonClient.getLock(UPLOAD_LOCK);
        lock.lock(10, TimeUnit.SECONDS);
        try {
            log.info("进入redisson分布式锁，商品开始上架...");
            seckillService.uploadSeckillSkuLastest3Days();
        } finally {
            lock.unlock();
        }
    }

}
