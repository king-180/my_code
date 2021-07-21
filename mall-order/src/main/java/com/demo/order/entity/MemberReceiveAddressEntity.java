package com.demo.order.entity;

import lombok.Data;

/**
 * @author wangxing
 * @date 2021/7/21 14:39
 */
@Data
public class MemberReceiveAddressEntity {

    private Long id;

    private Long memberId;

    private String name;

    private String phone;

    private String postCode;

    private String province;

    private String city;

    private String region;

    private String detailAddress;

    private String areaCode;

    private Integer defaultStatus;

}
