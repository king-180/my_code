package com.demo.order.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wangxing
 * @date 2021/7/21 14:47
 */
@Data
public class OrderItemVo {
    private Long skuId;

    private boolean check = true;

    private String title;

    private String image;

    private List<String> skuAttr;

    private Integer count;

    private BigDecimal price;

    private BigDecimal totalPrice;

    private BigDecimal weight;

}
