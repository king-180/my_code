package com.demo.cart.vo;

import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wangxing
 * @date 2021/7/21 9:51
 */
public class Cart {

    private List<CartItem> cartItems;

    private Integer countNum;

    private Integer typeNum;

    private BigDecimal totalAmount;

    private BigDecimal reduceAmount = new BigDecimal(0);

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Integer getCountNum() {
        int count = 0;
        if (CollectionUtils.isNotEmpty(this.cartItems)) {
            for (CartItem cartItem : this.cartItems) {
                count += cartItem.getCount();
            }
        }
        return count;
    }

    public Integer getTypeNum() {
        return typeNum;
    }

    public BigDecimal getTotalAmount() {
        BigDecimal amount = new BigDecimal(0);
        if (CollectionUtils.isNotEmpty(this.cartItems)) {
            for (CartItem cartItem : this.cartItems) {
                if (cartItem.isCheck()) {
                    amount = amount.add(cartItem.getTotalPrice());
                }
            }
        }
        BigDecimal finalAmount = amount.subtract(getReduceAmount());

        return finalAmount;
    }

    public BigDecimal getReduceAmount() {
        return reduceAmount;
    }

}
