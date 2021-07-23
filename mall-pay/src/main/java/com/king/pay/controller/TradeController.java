package com.king.pay.controller;

import com.king.pay.controller.base.BaseController;
import com.king.pay.service.ThirdTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangxing
 * @date 2021/7/23 19:27
 */
@RestController
public class TradeController extends BaseController {

    @Autowired
    private ThirdTradeService thirdTradeService;

    @RequestMapping("/preCreate")
    public String preCreate(Integer channel) {
        return thirdTradeService.preCreate(channel);
    }

}
