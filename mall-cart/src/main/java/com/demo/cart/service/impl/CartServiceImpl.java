package com.demo.cart.service.impl;

//import com.alibaba.fastjson.JSON;

import com.demo.cart.feign.ProductFeignService;
import com.demo.cart.interceptor.CartInterceptor;
import com.demo.cart.service.CartService;
import com.demo.cart.vo.Cart;
import com.demo.cart.vo.CartItem;
import com.demo.cart.vo.SkuInfoVo;
import com.demo.cart.vo.UserInfoTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @author wangxing
 * @date 2021/7/21 10:47
 */
@Slf4j
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProductFeignService productFeignService;

    @Autowired
    private static ThreadPoolExecutor threadPoolExecutor;

    private static final String CART_PREFIX = "gulimall:cart";

    @Override
    public CartItem addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException {
        BoundHashOperations<String, String, String> cartOps = getCartOps();

        String cartItemRedis = cartOps.get(skuId.toString());
        if (StringUtils.isEmpty(cartItemRedis)) {
            CartItem cartItem = new CartItem();
            // 购物车没有此商品，添加新商品到购物车
            // 1.用异步任务 调用远程服务查询当前要添加的商品的信息
            CompletableFuture<Void> skuInfoTask = CompletableFuture.runAsync(() -> {
                SkuInfoVo skuInfo = productFeignService.getSkuInfo(skuId);
                cartItem.setSkuId(skuId);
                cartItem.setTitle(skuInfo.getSkuTitle());
                cartItem.setPrice(skuInfo.getPrice());
                cartItem.setCount(num);
                cartItem.setImage(skuInfo.getSkuDefaultImage());
            }, threadPoolExecutor);
            // 2.用异步任务 调用远程服务查询sku组合信息
            CompletableFuture<Void> skuSaleAttrTask = CompletableFuture.runAsync(() -> {
                List<String> skuAttr = productFeignService.getSkuSaleAttr(skuId);
                cartItem.setSkuAttr(skuAttr);
            }, threadPoolExecutor);
            // 3.调用CompletableFuture.allOf().get() 等待两个异步任务都完成
            CompletableFuture.allOf(skuInfoTask, skuSaleAttrTask).get();

            String cartItemJsonStr = "JSON.toJSONString(cartItem)";
            cartOps.put(skuId.toString(), cartItemJsonStr);
            return cartItem;
        } else {
            // 购物车有此商品，无需添加商品，只需要加数量
            CartItem cartItem = new CartItem()/*JSON.parseObject(cartItemRedis, CartItem.class)*/;
            cartItem.setCount(cartItem.getCount() + num);
            String cartItemJsonStr = "" /*JSON.toJSONString(cartItem)*/;
            cartOps.put(skuId.toString(), cartItemJsonStr);
            return cartItem;
        }


    }

    @Override
    public CartItem getCartItem(Long skuId) {
        BoundHashOperations<String, String, String> cartOps = getCartOps();
        String carItemRedis = cartOps.get(skuId.toString());
        CartItem cartItem = new CartItem()/*JSON.parseObject(carItemRedis, CartItem.class)*/;
        return cartItem;
    }

    @Override
    public Cart getCart() throws ExecutionException, InterruptedException {
        Cart cart = new Cart();
        UserInfoTO userInfoTO = CartInterceptor.userLoginThreadLocal.get();
        if (userInfoTO.getUserId() != null) {
            // 已登录
            // 1.如果临时购物车的数据还没有合并到购物车中，先获取临时购物车的购物项
            String tmpCartKey = CART_PREFIX + userInfoTO.getUserKey();
            List<CartItem> tmpCartItems = getCartItems(tmpCartKey);
            if (CollectionUtils.isNotEmpty(tmpCartItems)) {
                for (CartItem cartItem : tmpCartItems) {
                    // 循环将临时购物车的商品添加到购物车
                    addToCart(cartItem.getSkuId(), cartItem.getCount());
                }
                // 清除临时购物车的数据
                clearCart(tmpCartKey);
            }
            // 2.获取登陆后的购物车购物项
            String cartKey = CART_PREFIX + userInfoTO.getUserId();
            List<CartItem> cartItems = getCartItems(cartKey);
            cart.setCartItems(cartItems);
        } else {
            // 未登录，获取临时购物车的购物项
            String cartKey = CART_PREFIX + userInfoTO.getUserKey();
            List<CartItem> cartItems = getCartItems(cartKey);
            cart.setCartItems(cartItems);
        }
        return cart;
    }

    @Override
    public void clearCart(String cartKey) {
        redisTemplate.delete(cartKey);
    }

    private BoundHashOperations<String, String, String> getCartOps() {
        UserInfoTO userInfoTO = CartInterceptor.userLoginThreadLocal.get();
        String cartKey = "";
        if (userInfoTO.getUserId() != null) {
            cartKey = CART_PREFIX + userInfoTO.getUserId();
        } else {
            cartKey = CART_PREFIX + userInfoTO.getUserKey();
        }
        return redisTemplate.boundHashOps(cartKey);
    }

    private List<CartItem> getCartItems(String cartKey) {
        BoundHashOperations<String, String, String> hashOps = redisTemplate.boundHashOps(cartKey);
        List<String> values = hashOps.values();
        if (CollectionUtils.isNotEmpty(values)) {
            List<CartItem> cartItems = values.stream().map((value) -> {
                CartItem cartItem = new CartItem()/*JSON.parseObject(value, CartItem.class)*/;
                return cartItem;
            }).collect(Collectors.toList());

            return cartItems;
        }
        return null;
    }


}
