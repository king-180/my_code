package com.wx.demo.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author wangxing
 * @date 2021/4/15 15:46
 */
@RestController
public class GoodController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${server.port}")
    private String serverPort;

    private static final String REDIS_LOCK = "atguigu";

    @Autowired
    private Redisson redisson;

    @GetMapping("/buy/good")
    public String buyGood() {

        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();

        RLock redissonLock = redisson.getLock(REDIS_LOCK);
        // 加锁
        redissonLock.lock();

        try {
            String result = stringRedisTemplate.opsForValue().get("good:001");
            int count = result == null ? 0 : Integer.parseInt(result);
            if (count > 0) {
                int realCount = count - 1;
                stringRedisTemplate.opsForValue().set("good:001", String.valueOf(realCount));
                System.out.println("端口号：" + serverPort + " --> 购买成功，库存：" + realCount + " 件");
                return "端口号：" + serverPort + " --> 购买成功，库存：" + realCount + " 件";
            } else {
                System.out.println("端口号：" + serverPort + " --> 购买失败，库存不足");
                return "sorry " + serverPort + " 购买失败，库存不足";
            }
        } finally {
            // 解锁，先确认当前状是锁住的状态
            if (redissonLock.isLocked()) {
                // 并且确认是当前线程持有锁
                if (redissonLock.isHeldByCurrentThread()) {
                    redissonLock.unlock();
                }
            }
        }
    }

}
