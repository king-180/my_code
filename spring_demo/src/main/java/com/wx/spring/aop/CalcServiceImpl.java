package com.wx.spring.aop;

import org.springframework.stereotype.Service;

/**
 * @author wangxing
 * @date 2021/4/14 13:08
 */
@Service
public class CalcServiceImpl implements CalcService {
    @Override
    public int div(int x, int y) {
        int result = x / y;
        System.out.println("        -------> CalcServiceImpl 被调用了... 结果：" + result);
        return result;
    }
}
