package com.wx.demo.auth.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangxing
 * @date 2021/7/20 10:30
 */
@Data
public class SocialUserVO implements Serializable {

    private String access_token;

    private String remind_in;

    private String expires_in;

    private String uid;

    private String isRealName;

}
