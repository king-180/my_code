package com.demo.order.vo;

import lombok.Data;

/**
 * @author wangxing
 * @date 2021/7/21 19:45
 */
@Data
public class MemberAddressVo {

    private String name;

    private String phone;

    private String province;

    private String city;

    private String areaCode;

    private String region;

    private String postCode;

    private String detailAddress;

    private Integer defaultStatus;
}
