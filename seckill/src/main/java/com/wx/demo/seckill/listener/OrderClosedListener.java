package com.wx.demo.seckill.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wangxing
 * @date 2021/7/19 16:31
 */
@Slf4j
@RabbitListener(queues = "order.release.order.queue")
@Component
public class OrderClosedListener {

    // 注入orderService
    @RabbitHandler
    public void listener() {
        log.info("OrderClosedListener.listener()...");
        // 手动调用支付宝收单
    }

}
