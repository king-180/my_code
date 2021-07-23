package com.king.pay.manager.base;

import com.king.pay.manager.config.ThirdTradeConfig;

/**
 * @author wangxing
 * @date 2021/7/23 18:47
 */
public interface ThirdConfigManager {

    String NOT_YET_IMPLEMENTED = "Not yet implemented";

    Integer getPayPlatform();

    ThirdTradeConfig loadConfig(String paymentAppId, String appkey);

    ThirdTradeConfig loadConfigByParams(String configStr);

}
