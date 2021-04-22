package com.wx.demo.to;

import lombok.Data;

/**
 * @author wangxing
 * @date 2021/4/22 17:20
 */
@Data
public class UserInfo {
    private Integer id;
    private String userKey;
    private boolean tmpUser = false;
}
