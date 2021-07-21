package com.wx.demo.auth.controller;

import com.alibaba.fastjson.JSON;
import com.wx.demo.auth.constant.AuthConstant;
import com.wx.demo.auth.util.HttpUtils;
import com.wx.demo.auth.vo.SocialUserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangxing
 * @date 2021/7/20 9:38
 */
@Controller
public class OAuth2Controller {

    @GetMapping("/oauth2.0/weibo/success")
    public String weibo(@RequestParam("code") String code, HttpSession session) {
        // http://gulimall.com/oauth2.0/weibo/success?code=awdsedscfdfrduhdd
        Map<String, Object> reqParamMap = new HashMap<>();
        reqParamMap.put("client_id", "2556547855");
        reqParamMap.put("client_secret", "5sad5dfds8affd5f6");
        reqParamMap.put("grant_type", "authorization_code");
        reqParamMap.put("redirect_uri", "http://gulimall.com/oauth2.0/weibo/success");
        reqParamMap.put("code", code);
        // 根据code换取accessToken
        String response = HttpUtils.doPost("https://api.weibo.com/oauth2/access_token", reqParamMap);
        SocialUserVO socialUserVO = JSON.parseObject(response, SocialUserVO.class);
        // R r = memberFeignService.oauthLogin(socialUserVo);
        // MemberRespVO memberRespVo = r.getData();
        // 保存到 Spring Redis session
        // 父域、子域 session 数据共享
        session.setAttribute(AuthConstant.LOGIN_USER, socialUserVO);
        // 登陆成功重定向到主页
        return "redirect:http://gulimall.com";
    }

}
