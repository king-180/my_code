package com.wx.demo.validator.anno;

import com.wx.demo.validator.IsNumberCheckValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * 字符串为数字，包含小数
 */
@Constraint(validatedBy = IsNumberCheckValidator.class)  // 关联解析类
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsNumberCheck {
    /**
     * @return
     */
    String value() default "";

    /**
     * 精度
     *
     * @return
     */
    int scale() default 0;

    /**
     * 错误信息
     *
     * @return
     */
    String message() default "字符串不合法，不是有效的数字或小数，或者小数位数超过{scale}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
