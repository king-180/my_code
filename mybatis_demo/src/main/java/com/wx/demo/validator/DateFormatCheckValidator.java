package com.wx.demo.validator;

import com.wx.demo.exception.ServiceException;
import com.wx.demo.util.DateUtilsEx;
import com.wx.demo.validator.anno.DateFormatCheck;
import org.apache.commons.lang3.StringUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.util.Date;

/**
 * @描述: 日期格式验证器
 * @作者: liuyulong
 * @创建时间: 2019/11/28 11:09 上午
 */
public class DateFormatCheckValidator implements ConstraintValidator<DateFormatCheck, String> {
    /**
     * 默认日期格式
     */
    private String dateFormat = "yyyyMMdd";

    @Override
    public void initialize(DateFormatCheck constraintAnnotation) {
        this.dateFormat = constraintAnnotation.format();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 校验长度
        if (dateFormat.length() == StringUtils.length(value)) {
            try {
                DateFormatCheckValidator.dateFormatFromStr(value, dateFormat);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 日期格式转换
     *
     * @param dateString
     * @param format
     * @return
     * @author liuyulong
     * @version [版本号, 2018年11月22日]
     */
    public static Date dateFormatFromStr(String dateString, String format) {
        if (StringUtils.isBlank(dateString)) {
            return null;
        }
        try {
            return DateUtilsEx.formatToDate(dateString, format);
        } catch (ParseException e) {
            throw new ServiceException("日期格式不合法");
        }
    }
}
