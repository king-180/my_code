package com.demo.order.web;

import com.demo.order.service.OrderService;
import com.demo.order.vo.OrderConfirmVo;
import com.demo.order.vo.OrderSubmitResponse;
import com.demo.order.vo.OrderSubmitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.ExecutionException;

/**
 * @author wangxing
 * @date 2021/7/21 14:50
 */
@Controller
public class OrderWebController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/toTrade")
    public String toTrade(Model model) {
        OrderConfirmVo orderConfirmVo = null;
        try {
            orderConfirmVo = orderService.confirmOrder();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        // 展示订单的数据
        model.addAttribute("orderConfirmVo", orderConfirmVo);
        return "confirm";
    }

    @PostMapping("/submitOrder")
    public String submitOrder(@RequestBody OrderSubmitVo orderSubmitVo) {
        // 去服务器创建订单，验证令牌、价格、锁库存...
        OrderSubmitResponse response = orderService.submitOrder(orderSubmitVo);
        if (response.getCode() == 200) {
            // 下单成功来到支付页
            return "pay";
        } else {
            // 下单失败回到订单确认页重新确认订单信息

            return "redirect:http://order.gulimall.com/toTrade";
        }
    }

}
