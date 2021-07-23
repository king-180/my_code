package com.king.pay.manager.base;

import com.king.pay.manager.config.ThirdTradeConfig;

/**
 * @author wangxing
 * @date 2021/7/23 18:47
 */
public abstract class BaseThirdConfigManager implements ThirdConfigManager {

    protected String paymentAppId;

    protected Integer payPlatform;

    public BaseThirdConfigManager(String paymentAppId, Integer payPlatform) {
        this.paymentAppId = paymentAppId;
        this.payPlatform = payPlatform;
    }

    @Override
    public Integer getPayPlatform() {
        return payPlatform;
    }

    @Override
    public ThirdTradeConfig loadConfig(String paymentAppId, String appkey) {
        return null;
    }

    @Override
    public ThirdTradeConfig loadConfigByParams(String configStr) {
        return null;
    }
}
