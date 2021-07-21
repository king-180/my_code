package com.demo.order.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wangxing
 * @date 2021/7/21 18:31
 */
@Data
public class OrderEntity {

    private Long id;

    private Long memberId;

    private String orderSn;

    private Long couponId;

    private Date createTime;

    private String memberUserName;

    private BigDecimal totalAmount;

    private BigDecimal payAmount;

    private BigDecimal freightAmount;

    private BigDecimal promotionAmount;

    private BigDecimal integrationAmount;

    private BigDecimal couponAmount;

    private BigDecimal discountAmount;

    private Integer sourceType;

    private Integer status;

    private String deliveryCompany;

    private String deliverSn;

    private Integer autoConfirmDay;

    private Integer integration;

    private Integer growth;

    private Integer billType;

    private String billHeader;

    private String billContent;

    private String billReceiverPhone;

    private String billReceiverEmail;

    private String receiverName;

    private String receiverPhone;

    private String receiverPostCode;

    private String receiverProvince;

    private String receiverCity;

    private String receiverRegion;

    private String receiverDetailAddress;

    private String note;

    private Integer confirmStatus;

    private Integer deleteStatus;

}
