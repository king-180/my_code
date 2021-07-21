package com.demo.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.expression.Lists;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangxing
 * @date 2021/7/20 16:34
 */
@RestController
public class HelloController {

    @Value("${sso.server.url}")
    private String ssoServerUrl;

    private static final List<String> emps = Arrays.asList("jack", "tom", "jerry", "lisa", "lucy", "pig");


    @RequestMapping("/hello")
    public String hello() {
        return "hello!";
    }

    @RequestMapping("/emp")
    public String doLogin(Model model, HttpSession session,
                          @RequestParam(value = "token", required = false) String token) {
        if (StringUtils.isNotBlank(token)) {

            model.addAttribute("emps", emps);
            return "success";
        }
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:" + ssoServerUrl + "?redirect_url=http://client1.com:8081/emp";
        } else {
            model.addAttribute("emps", emps);
            return "success";
        }
    }

}
