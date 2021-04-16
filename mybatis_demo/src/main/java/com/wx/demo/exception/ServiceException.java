package com.wx.demo.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * @author wangxing
 * @date 2021/4/14 18:26
 */
public class ServiceException extends RuntimeException {
    private String code;
    private Object data;
    private Object[] dataAry;

    public String getCode() {
        return this.code;
    }

    public Object getData() {
        return this.data;
    }

    public Object getDataAry() {
        return this.dataAry;
    }

    public ServiceException() {
    }

    public ServiceException(Throwable e) {
        super(e);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = String.valueOf(code);
    }

    public ServiceException(String code, String message, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public ServiceException(String code, Object[] dataAry) {
        this.code = code;
        this.dataAry = dataAry;
    }

    public ServiceException(String message, Throwable e) {
        super(message, e);
    }

    public String getMessage() {
        return StringUtils.isNotBlank(this.getCode()) && StringUtils.isBlank(super.getMessage()) ? super.getMessage() : super.getMessage();
    }
}

