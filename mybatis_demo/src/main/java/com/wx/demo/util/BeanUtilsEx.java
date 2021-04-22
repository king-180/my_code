package com.wx.demo.util;

import com.wx.demo.exception.ServiceException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.cglib.beans.BeanCopier;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangxing
 * @date 2021/4/22 11:03
 */
public class BeanUtilsEx {

    public BeanUtilsEx() {
    }

    public static <T> T convert(Object inObj, Class<T> clz) {
        return (T) (new BeanConverter()).convert(clz, inObj);
    }


    public static boolean isSimpleObject(Object o) {
        return o != null && ClassUtilsEx.isSimpleClz(o.getClass());
    }

    static {
        BeanUtilsEx.BeanConverter converter = new BeanUtilsEx.BeanConverter();
        ConvertUtils.register(converter, Date.class);
        ConvertUtils.register(converter, BigDecimal.class);
        ConvertUtils.register(converter, String.class);
        ConvertUtils.register(converter, Map.class);
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        Map<String, BeanCopier> bcMap = new ConcurrentHashMap(16);
    }

    private static class BeanConverter implements org.apache.commons.beanutils.Converter {
        private BeanConverter() {
        }

        public Object convert(Class type, Object value) {
            if (value == null) {
                return null;
            } else if (!value.getClass().equals(type) && !type.isAssignableFrom(value.getClass())) {
                if (type.equals(String.class)) {
                    return value instanceof Date ? DateUtilsEx.formatToString((Date) value, "yyyy.MM.dd HH:mm:ss") : String.valueOf(value);
                } else {
                    if (type.equals(Date.class)) {
                        if (value instanceof Long) {
                            return new Date((Long) value);
                        }

                        if (value instanceof String) {
                            int lengthb = ((String) value).getBytes().length;

                            try {
                                if (lengthb == "yyyy.MM.dd".getBytes().length) {
                                    return DateUtilsEx.formatToDate(value.toString(), "yyyy.MM.dd");
                                }

                                if (lengthb == "yyyy.MM.dd HH:mm:ss".getBytes().length) {
                                    return DateUtilsEx.formatToDate(String.valueOf(value), "yyyy.MM.dd HH:mm:ss");
                                }

                                if (lengthb == "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'".getBytes().length - 4) {
                                    return DateUtilsEx.formatToDate(String.valueOf(value), "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
                                }

                                if (lengthb == 0) {
                                    return null;
                                }
                            } catch (ParseException var6) {
                                throw new ServiceException("类型转化出现异常:" + var6);
                            }
                        }
                    } else if (type.equals(BigDecimal.class)) {
                        if (StringUtils.isBlank(String.valueOf(value))) {
                            return null;
                        }

                        if (BeanUtilsEx.isSimpleObject(value)) {
                            try {
                                return new BigDecimal(String.valueOf(value));
                            } catch (NumberFormatException var5) {
                                throw new ServiceException(value + "转化数值发生异常");
                            }
                        }
                    } else if (type.equals(Long.class)) {
                        if (StringUtils.isBlank(String.valueOf(value))) {
                            return null;
                        }

                        if (BeanUtilsEx.isSimpleObject(value)) {
                            return new Long(String.valueOf(value));
                        }
                    } else if (type.equals(Double.class)) {
                        if (StringUtils.isBlank(String.valueOf(value))) {
                            return null;
                        }

                        if (BeanUtilsEx.isSimpleObject(value)) {
                            return new Double(String.valueOf(value));
                        }
                    } else if (type.equals(Integer.class)) {
                        if (BeanUtilsEx.isSimpleObject(value)) {
                            if (value instanceof String && StringUtils.isBlank((String) value)) {
                                return null;
                            }

                            return new Integer(String.valueOf(value));
                        }
                    } else {
                        if (type.equals(Boolean.class)) {
                            if (StringUtils.isBlank(String.valueOf(value))) {
                                return Boolean.FALSE;
                            }

                            if (!"1".equals(value) && !"true".equals(value)) {
                                return Boolean.FALSE;
                            }

                            return Boolean.TRUE;
                        }

                        if (type.equals(Map.class)) {
                            if (!BeanUtilsEx.isSimpleObject(value)) {
                                return this.bean2map(value);
                            }
                        } else if (value instanceof Map) {
                            return this.map2bean((Map) value, type);
                        }
                    }

                    throw new ServiceException("无法将对象" + value + "转化为" + type.getName() + "类型!");
                }
            } else {
                return value;
            }
        }

        private Object map2bean(Map<String, Object> srcMap, Class type) {
            Map propertyMap = ClassUtilsEx.getClassPropertyType(type);

            Object destObject;
            try {
                destObject = type.newInstance();
            } catch (Exception var10) {
                throw new ServiceException("转型时无法实例化" + type.getName());
            }

            Iterator var5 = propertyMap.keySet().iterator();

            while (true) {
                String propertyName;
                do {
                    if (!var5.hasNext()) {
                        return destObject;
                    }

                    propertyName = (String) var5.next();
                } while (!srcMap.containsKey(propertyName) && !srcMap.containsKey(propertyName.toLowerCase()));

                Object propertyValue = srcMap.containsKey(propertyName) ? srcMap.get(propertyName) : srcMap.get(propertyName.toLowerCase());
                if (propertyValue != null) {
                    propertyValue = BeanUtilsEx.convert(propertyValue, (Class) propertyMap.get(propertyName));

                    try {
                        if (propertyValue != null) {
                            BeanUtils.setProperty(destObject, propertyName, propertyValue);
                        }
                    } catch (Exception var9) {
                        throw new ServiceException(var9);
                    }
                }
            }
        }

        private Map<String, Object> bean2map(Object value) {
            HashMap result = new HashMap(16);

            Map propertyMap;
            try {
                propertyMap = PropertyUtils.describe(value);
            } catch (Exception var6) {
                throw new ServiceException(var6);
            }

            propertyMap.remove("class");
            Iterator var4 = propertyMap.keySet().iterator();

            while (var4.hasNext()) {
                String propertyName = (String) var4.next();
                result.put(propertyName, propertyMap.get(propertyName));
            }

            return result;
        }
    }

    /**
     * 属性对拷，与 org.springframework.beans.BeanUtils.copyProperties(source, target) 的一致
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
    }

    /**
     * 复制拷贝属性的时候忽略 source中 null 值
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    /**
     * 获取源对象中所有为 null 的字段String数组
     *
     * @param source 源对象
     * @return 所有为 null 的字段String数组
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
