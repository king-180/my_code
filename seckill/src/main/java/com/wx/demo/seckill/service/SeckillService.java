package com.wx.demo.seckill.service;

import com.wx.demo.seckill.to.SeckillSkuRedisTO;

import java.util.List;

/**
 * @author wangxing
 * @date 2021/7/19 0:35
 */
public interface SeckillService {
    void uploadSeckillSkuLastest3Days();

    List<SeckillSkuRedisTO> getCurrentSeckillSkus();

    SeckillSkuRedisTO getSkuSeckillInfo(Long skuId);

    String kill(String killId, String key, Integer num);
}
