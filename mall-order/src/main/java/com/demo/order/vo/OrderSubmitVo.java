package com.demo.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wangxing
 * @date 2021/7/21 18:23
 */
@Data
public class OrderSubmitVo {

    private Long addrId;

    private Integer payType;

    private String orderToken;

    private BigDecimal payPrice;

    private String note;

}
