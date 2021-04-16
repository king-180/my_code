package com.wx.demo.vo;

/**
 * @author wangxing
 * @date 2021/4/16 16:21
 */
public class ResultVO<T> {

    private int code;
    private String msg;
    private T data;

    public ResultVO() {
    }

    public ResultVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResultVO(T data) {
        this.data = data;
    }

    public ResultVO(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
