package com.wx.demo.util;

import com.wx.demo.exception.ServiceException;
import com.wx.demo.validator.anno.sequence.DefaultCheckSequence;
import org.apache.commons.lang3.ArrayUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

/**
 * @描述: 参数校验工具类
 * @作者: liuyulong
 * @创建时间: 2019/12/30 9:57 上午
 */
public class ValidateUtils {
    private static Validator validator;

    /**
     * 验证参数，抛出定义的异常信息[追加校验顺序]
     *
     * @param object 需要校验的实例，对应类中需要校验的变量需要追加验证器的注解
     * @param groups 校验组
     * @param <T>
     */
    public static <T> void checkParametersBySequence(T object, Class<?>... groups) {
        // 追加校验顺序
        int arraySize = 1;
        if (ArrayUtils.isNotEmpty(groups)) {
            arraySize += groups.length;
        }
        Class[] extendGroups = new Class[arraySize];
        extendGroups[0] = DefaultCheckSequence.class;
        int idx = 1;
        if (ArrayUtils.isNotEmpty(groups)) {
            for (Class clazz : groups) {
                extendGroups[idx] = clazz;
                idx++;
            }
        }
        checkParameters(object, extendGroups);
    }

    /**
     * 验证参数，抛出定义的异常信息
     *
     * @param object 需要校验的实例，对应类中需要校验的变量需要追加验证器的注解
     * @param groups 校验组
     * @param <T>
     */
    public static <T> void checkParameters(T object, Class<?>... groups) {
        Set<ConstraintViolation<T>> violateResultSet = validate(object, groups);
        Optional<ConstraintViolation<T>> violateResult = violateResultSet.stream().findFirst();
        // 是否存在校验不通过
        if (violateResult.isPresent()) {
            // 抛出异常信息
            throw new ServiceException(violateResult.get().getMessage());
        }
    }

    /**
     * 参数校验
     *
     * @param object 需要校验的实例，对应类中需要校验的变量需要追加验证器的注解
     * @param groups 校验组
     * @param <T>
     * @return
     */
    public static <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
        if (object == null) {
            throw new ServiceException("validate object should not be null.");
        }

        if (validator == null) {
            validator = SpringBeanUtil.getBean(Validator.class);
        }
        return validator.validate(object, groups);
    }
}
