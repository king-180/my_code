package com.wx.spring.aop;

import com.wx.spring.SpringDemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

/**
 * @author wangxing
 * @date 2021/4/14 13:17
 */
@SpringBootTest(classes = SpringDemoApplication.class)
//@RunWith(SpringRunner.class) // spring4需要这一行
public class AOPTest {

    @Autowired
    private CalcService calcService;

    @Test
    public void testAop4() {
        System.out.println("spring 的版本：" + SpringVersion.getVersion() + " springboot 的版本：" + SpringBootVersion.getVersion());
        System.out.println();
        calcService.div(10, 5);
    }

    @Test
    public void testAop5() {
        System.out.println("spring 的版本：" + SpringVersion.getVersion() + " springboot 的版本：" + SpringBootVersion.getVersion());
        System.out.println();
        calcService.div(10, 5);
    }

}