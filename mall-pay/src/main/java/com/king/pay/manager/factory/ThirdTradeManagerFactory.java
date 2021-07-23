package com.king.pay.manager.factory;

import com.king.pay.manager.AlipayManager;
import com.king.pay.manager.JdpayManager;
import com.king.pay.manager.WxpayManager;
import com.king.pay.manager.base.ThirdPayManager;

/**
 * @author wangxing
 * @date 2021/7/23 19:11
 */
public class ThirdTradeManagerFactory {

    public static ThirdPayManager getInstance(Integer channel) {
        switch (channel) {
            case 1:
                return new AlipayManager(null, null);
            case 2:
                return new JdpayManager(null, null);
            case 3:
                return new WxpayManager(null, null);
            default:
                throw new RuntimeException("支付方式不支持！");
        }
    }

}
