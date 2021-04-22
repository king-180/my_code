package com.wx.demo.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wx.demo.entity.Book;
import com.wx.demo.exception.ServiceException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wangxing
 * @date 2021/4/22 10:49
 */
public class JSONUtilsEx {

    private static final ObjectMapper defaultMapper = new ObjectMapper();

    static {
        defaultMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        defaultMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        defaultMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    }

    private JSONUtilsEx() {

    }

    /**
     * 序列化对象成json字符串格式
     *
     * @param obj 对象
     * @return 对象对应json字符串格式
     */
    public static String serialize(Object obj) {
        return serialize(obj, defaultMapper);
    }

    public static String serialize(Object obj, ObjectMapper mapper) {
        StringWriter writer;

        try {
            writer = new StringWriter();
            mapper.writeValue(writer, obj);
            writer.close();
        } catch (Exception var4) {
            throw new ServiceException("JSON序列化结果异常:" + var4.getMessage());
        }

        return writer.toString();
    }

    /**
     * 反序列化json字符串生成指定对象格式
     *
     * @param jsonStr json字符串
     * @param clazz   反序列化目标对象类
     * @param <T>     目标对象类型
     * @return 对象
     */
    public static <T> T deserialize(String jsonStr, Class<T> clazz) {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        } else {
            try {
                return defaultMapper.readValue(jsonStr.replace("\n", ""), clazz);
            } catch (Exception var3) {
                throw new ServiceException("JSON反序列化结果异常:" + var3.getMessage());
            }
        }
    }

    /**
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> deserializeList(String jsonStr, Class<T> clazz) {
        List<T> result = new ArrayList(10);
        List<Map> resultList = (List) deserialize(jsonStr, List.class);
        if (CollectionUtils.isNotEmpty(resultList)) {

            for (Map map : resultList) {
                result.add(BeanUtilsEx.convert(map, clazz));
            }
        }

        return result;
    }

}
