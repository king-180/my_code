package com.wx.demo.seckill.scheduled;

import com.wx.demo.seckill.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author wangxing
 * @date 2021/7/19 0:31
 */
@Slf4j
@Service
public class SeckillSkuScheduled {

    @Autowired
    private SeckillService seckillService;

    /**
     * 秒杀上架：每天晚上3：00 上架最近三天需要秒杀的商品
     */
    @Scheduled(cron = "* * 3 * * ?")
    public void uploadSeckillSkuLastest3Days() {
        // 重复上架无需处理
        seckillService.uploadSeckillSkuLastest3Days();
    }

}
