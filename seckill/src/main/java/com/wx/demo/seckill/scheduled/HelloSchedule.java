package com.wx.demo.seckill.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author wangxing
 * @date 2021/7/18 23:16
 */
@Slf4j
@Component
@EnableAsync
@EnableScheduling
public class HelloSchedule {

    /**
     * spring 的定时任务只有6位，不能指定年
     * 任务不应该阻塞
     * 1.定时任务：
     * 1）让业务以异步方式运行，提交到线程池
     * 2）定时任务线程池，设置 TaskScheduleProperties
     * 3) 让定时任务异步执行
     * 自动配置类 TaskSchedulingAutoConfiguration
     * 2.异步任务：
     * Async
     * EnableAsync
     * 自动配置类 TaskExecutionAutoConfiguration
     * <p>
     * 使用 异步任务+定时任务 实现定时任务不阻塞
     */
    @Async
    @Scheduled(cron = "*/5 * * * * ?")
    public void hello() {
        log.info("hello...");
        try {
            TimeUnit.SECONDS.sleep(7L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
