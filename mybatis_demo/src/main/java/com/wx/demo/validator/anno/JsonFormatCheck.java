package com.wx.demo.validator.anno;



import com.wx.demo.validator.JsonFormatCheckValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * json字符串格式校验注解
 */
@Constraint(validatedBy = JsonFormatCheckValidator.class)  // 关联解析类
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonFormatCheck {
    /**
     * @return
     */
    String value() default "";

    /**
     * 错误信息
     *
     * @return
     */
    String message() default "字符串不是合法的json字符串";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
