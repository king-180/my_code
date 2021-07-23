package com.king.pay.manager.base;

import java.math.BigDecimal;

/**
 * @author wangxing
 * @date 2021/7/23 18:44
 */
public abstract class BaseThirdPayManager extends BaseThirdConfigManager implements ThirdPayManager {
    public BaseThirdPayManager(String paymentAppId, Integer payPlatform) {
        super(paymentAppId, payPlatform);
    }

    @Override
    public String preCreate(Integer tradeNo, Integer channel, BigDecimal totalAmount) {
        throw new RuntimeException(NOT_YET_IMPLEMENTED);
    }

    @Override
    public String pay(Integer tradeNo, Integer orderId, Integer channel, BigDecimal totalAmount) {
        throw new RuntimeException(NOT_YET_IMPLEMENTED);
    }

    @Override
    public String close(Integer tradeNo, Integer orderId) {
        throw new RuntimeException(NOT_YET_IMPLEMENTED);
    }

    @Override
    public String OrderQuery(Integer tradeNo, Integer orderId) {
        throw new RuntimeException(NOT_YET_IMPLEMENTED);
    }

    @Override
    public String refund(Integer tradeNo, Integer orderId) {
        throw new RuntimeException(NOT_YET_IMPLEMENTED);
    }

    @Override
    public String refundQuery(Integer tradeNo, Integer orderId) {
        throw new RuntimeException(NOT_YET_IMPLEMENTED);
    }

    @Override
    public String confirmOrder(Integer orderId) {
        throw new RuntimeException(NOT_YET_IMPLEMENTED);
    }

    @Override
    public String cancelOrder(Integer orderId) {
        throw new RuntimeException(NOT_YET_IMPLEMENTED);
    }

    @Override
    public String receiptOrder(Integer orderId) {
        throw new RuntimeException(NOT_YET_IMPLEMENTED);
    }
}
