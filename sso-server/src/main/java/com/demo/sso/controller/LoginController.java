package com.demo.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author wangxing
 * @date 2021/7/20 16:34
 */
@RestController
public class LoginController {

    @RequestMapping("/login.html")
    public String toLogin(@RequestParam("redirect_url") String url, Model model) {
        model.addAttribute("url", url);
        return "login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("url") String url) {
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            // 登陆成功，重定向到之前想要访问的页面，并且先将登陆成功的用户存起来
            String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
            return "redirect:" + url + "?token=" + uuid;
        } else {
            // 登陆失败
            return "failed";
        }
    }

}
