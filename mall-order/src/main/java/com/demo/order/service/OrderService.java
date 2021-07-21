package com.demo.order.service;

import com.demo.order.vo.OrderConfirmVo;
import com.demo.order.vo.OrderSubmitResponse;
import com.demo.order.vo.OrderSubmitVo;

import java.util.concurrent.ExecutionException;

/**
 * @author wangxing
 * @date 2021/7/21 14:51
 */
public interface OrderService {

    OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException;

    OrderSubmitResponse submitOrder(OrderSubmitVo orderSubmitVo);
}
