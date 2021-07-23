package com.king.pay.manager.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxing
 * @date 2021/7/23 19:12
 */
public class ThirdTradeConfig {

    private List<Integer> channels = new ArrayList<>();

    private String paymentAppId;

    public String getPaymentAppId() {
        return paymentAppId;
    }

    public void setPaymentAppId(String paymentAppId) {
        this.paymentAppId = paymentAppId;
    }

    public List<Integer> getChannels() {
        return channels;
    }

}
