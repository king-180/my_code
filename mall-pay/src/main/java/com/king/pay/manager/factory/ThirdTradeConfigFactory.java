package com.king.pay.manager.factory;

import com.king.pay.manager.config.AlipayConfig;
import com.king.pay.manager.config.JdpayConfig;
import com.king.pay.manager.config.ThirdTradeConfig;
import com.king.pay.manager.config.WxpayConfig;

/**
 * @author wangxing
 * @date 2021/7/23 19:11
 */
public class ThirdTradeConfigFactory {

    public static ThirdTradeConfig getInstant(Integer channel) {
        switch (channel) {
            case 1:
                return new AlipayConfig();
            case 2:
                return new JdpayConfig();
            case 3:
                return new WxpayConfig();
            default:
                throw new RuntimeException("支付方式不支持！");
        }
    }

}
