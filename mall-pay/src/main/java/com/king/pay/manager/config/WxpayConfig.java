package com.king.pay.manager.config;

/**
 * @author wangxing
 * @date 2021/7/23 19:55
 */
public class WxpayConfig implements ThirdTradeConfig {

    @Override
    public Integer getChannel() {
        return 3;
    }

}
