package com.wx.demo.validator;


import com.wx.demo.validator.anno.IsNumberCheck;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 * @author liuyulong
 * @description 字符串为数字，包含小数
 * @date 2020-08-12
 **/
public class IsNumberCheckValidator implements ConstraintValidator<IsNumberCheck, String> {

    private int scale = 0;

    @Override
    public void initialize(IsNumberCheck constraintAnnotation) {
        this.scale = constraintAnnotation.scale();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            return false;
        }
        try {
            // 字符串为数字，包含小数
            BigDecimal result = new BigDecimal(value);
            // 精度校验
            return result.scale() <= this.scale;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
