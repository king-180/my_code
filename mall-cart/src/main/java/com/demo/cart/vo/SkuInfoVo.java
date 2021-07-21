package com.demo.cart.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wangxing
 * @date 2021/7/21 11:15
 */
@Data
public class SkuInfoVo {

    private Long skuId;

    private Long spuId;

    private String skuName;

    private String skuDesc;

    private Long catalogId;

    private Long brandId;

    private String skuDefaultImage;

    private String skuTitle;

    private BigDecimal price;

    private Long saleCount;

}
