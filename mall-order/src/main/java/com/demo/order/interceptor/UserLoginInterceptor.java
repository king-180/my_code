package com.demo.order.interceptor;

import com.demo.order.vo.MemberRespVO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author wangxing
 * @date 2021/7/21 14:24
 */
@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    public static final ThreadLocal<MemberRespVO> loginUserThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        MemberRespVO loginUser = (MemberRespVO) session.getAttribute("loginUser");
        if (loginUser != null) {
            loginUserThreadLocal.set(loginUser);
            return true;
        } else {
            response.sendRedirect("http://auth.gulimall.com/login.html");
            return false;
        }
    }
}
