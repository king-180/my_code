package com.king.pay.manager.base;

import java.math.BigDecimal;

/**
 * @author wangxing
 * @date 2021/7/23 18:46
 */
public interface ThirdPayManager extends ThirdConfigManager {

    String preCreate(Integer tradeNo, Integer channel, BigDecimal totalAmount);

    String pay(Integer tradeNo, Integer orderId, Integer channel, BigDecimal totalAmount);

    String close(Integer tradeNo, Integer orderId);

    String OrderQuery(Integer tradeNo, Integer orderId);

    String refund(Integer tradeNo, Integer orderId);

    String refundQuery(Integer tradeNo, Integer orderId);

    String confirmOrder(Integer orderId);

    String cancelOrder(Integer orderId);

    String receiptOrder(Integer orderId);

}
