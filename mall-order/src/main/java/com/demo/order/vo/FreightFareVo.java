package com.demo.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wangxing
 * @date 2021/7/21 19:35
 */
@Data
public class FreightFareVo {

    private MemberAddressVo address;

    private BigDecimal fare;

}
