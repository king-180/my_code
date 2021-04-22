package com.wx.demo.interceptor;

import com.wx.demo.entity.UserEntity;
import com.wx.demo.to.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author wangxing
 * @date 2021/4/20 15:31
 */
@Slf4j
public class MyCartInterceptor implements HandlerInterceptor {

    // ThreadLocal 同一个线程间数据共享
    public static ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle() ... 执行");
        UserInfo userInfo = new UserInfo();
        HttpSession session = request.getSession();
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");

        if (loginUser != null) {
            // 用户登陆了
            userInfo.setId(loginUser.getId());
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("tmp-user".equals(name)) {
                    userInfo.setUserKey(cookie.getValue());
                }
            }
        }
        // 没有临时用户，一定分配一个临时用户
        if (StringUtils.isEmpty(userInfo.getUserKey())) {
            String uuid = UUID.randomUUID().toString();
            userInfo.setUserKey(uuid);
        }
        // 目标方法执行之前
        threadLocal.set(userInfo);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 业务执行之后, 分配临时用户，让浏览器保存
        UserInfo userInfo = threadLocal.get();
        if (!userInfo.isTmpUser()) {
            Cookie cookie = new Cookie("tmp-user", userInfo.getUserKey());
            cookie.setDomain("gulimall.com");
            cookie.setMaxAge(60 * 60 * 24 * 30);

            response.addCookie(cookie);
        }
        log.info("postHandle() .... 执行，modelAndView = {}", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion() .... 执行, ex = {}", ex);
    }
}
