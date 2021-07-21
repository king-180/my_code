package com.demo.cart.service;

import com.demo.cart.vo.Cart;
import com.demo.cart.vo.CartItem;

import java.util.concurrent.ExecutionException;

/**
 * @author wangxing
 * @date 2021/7/21 10:46
 */
public interface CartService {

    CartItem addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException;

    CartItem getCartItem(Long skuId);

    Cart getCart() throws ExecutionException, InterruptedException;

    void clearCart(String cartKey);
}
