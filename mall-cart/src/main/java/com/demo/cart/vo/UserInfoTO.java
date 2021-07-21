package com.demo.cart.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author wangxing
 * @date 2021/7/21 9:54
 */
@ToString
@Data
public class UserInfoTO {

    private Long userId;

    private String userKey;

    private boolean tmpUser = false;

}
