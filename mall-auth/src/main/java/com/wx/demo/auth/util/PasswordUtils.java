package com.wx.demo.auth.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author wangxing
 * @date 2021/7/19 22:00
 */
public class PasswordUtils {

    private static final BCryptPasswordEncoder passwordEncoder;

    static {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * 明文盐值加密
     *
     * @param rawPassword 表单明文
     * @return 密文
     */
    public static String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * 判断明文和密文是否匹配
     *
     * @param rawPassword     表单中的明文
     * @param encodedPassword 数据库中的密文
     * @return true or false
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
