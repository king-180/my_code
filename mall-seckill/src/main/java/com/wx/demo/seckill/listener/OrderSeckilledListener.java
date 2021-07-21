package com.wx.demo.seckill.listener;

import com.rabbitmq.client.Channel;
import com.wx.demo.seckill.mq.SeckillOrderTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wangxing
 * @date 2021/7/19 16:31
 */
@Slf4j
@RabbitListener(queues = "order.seckill.order.queue")
@Component
public class OrderSeckilledListener {

    // 注入 orderService
    @RabbitHandler
    public void listener(SeckillOrderTO seckillOrder, Channel channel, Message message) {
        log.info("OrderPayedListener.listener()...");
        // orderService.createSkillOrder(seckillOrder)
    }

}
