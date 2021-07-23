package com.king.pay.manager;

import com.king.pay.manager.base.BaseThirdPayManager;
import com.king.pay.manager.config.ThirdTradeConfig;

import java.math.BigDecimal;

/**
 * @author wangxing
 * @date 2021/7/23 18:54
 */
public class JdpayManager extends BaseThirdPayManager {

    public JdpayManager(String paymentAppId, Integer payPlatform) {
        super(paymentAppId, payPlatform);
    }

    @Override
    public Integer getPayPlatform() {
        return null;
    }

    @Override
    public ThirdTradeConfig loadConfig(String paymentAppId, String appkey) {
        return null;
    }

    @Override
    public ThirdTradeConfig loadConfigByParams(String configStr) {
        return null;
    }

    @Override
    public String preCreate(Integer tradeNo, Integer channel, BigDecimal totalAmount) {
        return null;
    }

    @Override
    public String pay(Integer tradeNo, Integer orderId, Integer channel, BigDecimal totalAmount) {
        return null;
    }

    @Override
    public String close(Integer tradeNo, Integer orderId) {
        return null;
    }

    @Override
    public String OrderQuery(Integer tradeNo, Integer orderId) {
        return null;
    }

    @Override
    public String refund(Integer tradeNo, Integer orderId) {
        return null;
    }

    @Override
    public String refundQuery(Integer tradeNo, Integer orderId) {
        return null;
    }

    @Override
    public String confirmOrder(Integer orderId) {
        return null;
    }

    @Override
    public String cancelOrder(Integer orderId) {
        return null;
    }

    @Override
    public String receiptOrder(Integer orderId) {
        return null;
    }
}
