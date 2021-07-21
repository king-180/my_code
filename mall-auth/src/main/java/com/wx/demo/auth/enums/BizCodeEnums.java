package com.wx.demo.auth.enums;

/**
 * @author wangxing
 * @date 2021/7/19 20:47
 */
public enum BizCodeEnums {
    /**
     * 系统未知异常
     */
    UNKNOWN_EXCEPTION(10000, "系统未知异常"),
    /**
     * 参数格式校验失败
     */
    VALID_EXCEPTION(10001, "参数格式校验失败"),
    /**
     * 短信验证码发送频率过高
     */
    SMS_CODE_EXCEPTION(10002, "短信验证码发送频率过高"),
    /**
     * 商品上架异常
     */
    PRODUCT_UP_EXCEPTION(11000, "商品上架异常");


    private int code;

    private String msg;

    BizCodeEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
