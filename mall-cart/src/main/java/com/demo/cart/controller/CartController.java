package com.demo.cart.controller;

import com.demo.cart.feign.ProductFeignService;
import com.demo.cart.interceptor.CartInterceptor;
import com.demo.cart.service.CartService;
import com.demo.cart.vo.Cart;
import com.demo.cart.vo.CartItem;
import com.demo.cart.vo.UserInfoTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.ExecutionException;

/**
 * @author wangxing
 * @date 2021/7/21 9:47
 */
@Slf4j
@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/cart.html")
    public String cartListPage() {

        UserInfoTO userInfoTO = CartInterceptor.userLoginThreadLocal.get();
        log.info("用户登陆信息：{}", userInfoTO);

        return "cartList";
    }

    @GetMapping("addToCart")
    public String addToCart(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num, RedirectAttributes attributes) {
        try {
            cartService.addToCart(skuId, num);
            attributes.addAttribute("skuId", skuId);
            return "redirect:http://cart.gulimall.com/addToCartSuccess.html";
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/addToCartSuccess.html")
    public String addToCartSuccessPage(@RequestParam("skuId") Long skuId, Model model) {
        // 重定向到成功页面，再次查询购物车数量
        CartItem cartItem = cartService.getCartItem(skuId);
        model.addAttribute("cartItem", cartItem);
        return "success";
    }

    @GetMapping("/getCart")
    public Cart getCart() {
        Cart cart = null;
        try {
            cart = cartService.getCart();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return cart;
    }

}
