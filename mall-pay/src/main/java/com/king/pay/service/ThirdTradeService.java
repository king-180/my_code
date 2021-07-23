package com.king.pay.service;

import com.king.pay.manager.base.ThirdPayManager;
import com.king.pay.manager.factory.ThirdTradeManagerFactory;
import org.springframework.stereotype.Service;

/**
 * @author wangxing
 * @date 2021/7/23 18:43
 */
@Service
public class ThirdTradeService {

    private ThirdPayManager thirdPayManager;

    public String preCreate(Integer channel) {
        thirdPayManager = ThirdTradeManagerFactory.getInstance(channel);
        String response = thirdPayManager.preCreate(null, channel, null);
        return response;
    }

    public String pay(Integer channel) {
        thirdPayManager = ThirdTradeManagerFactory.getInstance(channel);
        String response = thirdPayManager.pay(null, null, channel, null);
        return response;
    }

}
