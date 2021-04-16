package com.wx.demo.validator;

import com.alibaba.fastjson.JSON;
import com.wx.demo.validator.anno.JsonFormatCheck;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author liuyulong
 * @description json字符串格式验证器
 * @date 2020-08-12
 **/
public class JsonFormatCheckValidator implements ConstraintValidator<JsonFormatCheck, String> {
    @Override
    public void initialize(JsonFormatCheck constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            return false;
        }
        // 是否为json格式的字符串
        return JsonFormatCheckValidator.isJson(value);
    }

    /**
     * 是否为json字符串
     *
     * @param content 待校验字符串
     * @return true:是，false:否
     */
    public static boolean isJson(String content) {
        return isJsonObject(content) || isJsonArray(content);
    }

    /**
     * 是否为json对象
     *
     * @param content json字符串
     * @return
     */
    private static boolean isJsonObject(String content) {
        try {
            JSON.parseObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 是否为json数组
     *
     * @param content json字符串
     * @return
     */
    public static boolean isJsonArray(String content) {
        try {
            JSON.parseArray(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
