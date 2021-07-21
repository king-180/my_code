package com.wx.demo.seckill.interceptor;

import com.wx.demo.seckill.vo.MemberRespVO;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangxing
 * @date 2021/7/19 15:06
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    public static ThreadLocal<MemberRespVO> loginUser = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // /order/status/155235555
        String uri = request.getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean match = antPathMatcher.match("/kill", uri);
        if (match) {
            // TODO
            MemberRespVO memberRespVo = (MemberRespVO) request.getSession().getAttribute("");
            if (memberRespVo != null) {
                loginUser.set(memberRespVo);
                return true;
            } else {
                // 没有登陆,重定向到登陆页
                request.setAttribute("msg", "请先登陆！");
                response.sendRedirect("http://127.0.0.1:8080/login.html");
            }
        }
        return false;
    }
}
