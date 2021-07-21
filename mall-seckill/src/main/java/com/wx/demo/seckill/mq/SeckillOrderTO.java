package com.wx.demo.seckill.mq;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wangxing
 * @date 2021/7/19 16:06
 */
@Data
public class SeckillOrderTO {

    /**
     * 订单号
     */
    private String orderSn;

    private Long promotionSessionId;

    private Long skuId;

    private BigDecimal seckillPrice;

    /**
     * 购买数量
     */
    private Integer num;

    private Long memberId;

}
