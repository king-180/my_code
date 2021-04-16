package com.wx.demo.validator.anno;



import com.wx.demo.validator.DateFormatCheckValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @描述: 日期格式校验注解
 * @作者: liuyulong
 * @创建时间: 2019/11/28 11:11 上午
 */
@Constraint(validatedBy = DateFormatCheckValidator.class)  // 关联解析类
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormatCheck {
    /**
     * 日期格式，默认yyyyMMdd
     *
     * @return
     */
    String format() default "yyyyMMdd";

    /**
     * 错误信息
     *
     * @return
     */
    String message() default "日期格式不合法，所需格式为:{format}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
