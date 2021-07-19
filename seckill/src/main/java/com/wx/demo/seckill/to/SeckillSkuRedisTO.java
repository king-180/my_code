package com.wx.demo.seckill.to;

import com.wx.demo.seckill.vo.SeckillSkuInfoVO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wangxing
 * @date 2021/7/19 10:55
 */
@Data
public class SeckillSkuRedisTO {

    private Long promotionSessionId;

    private Long skuId;

    private SeckillSkuInfoVO skuInfoVO;

    private String randomCodeToken;

    private BigDecimal secskillPrice;

    private int seckillLimit;

    private Long startTime;

    private Long endTime;

}
