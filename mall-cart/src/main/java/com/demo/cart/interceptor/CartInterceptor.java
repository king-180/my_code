package com.demo.cart.interceptor;

import com.demo.cart.vo.MemberRespVO;
import com.demo.cart.vo.UserInfoTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author wangxing
 * @date 2021/7/21 9:47
 */
@Slf4j
@Component
public class CartInterceptor implements HandlerInterceptor {

    public static ThreadLocal<UserInfoTO> userLoginThreadLocal = new ThreadLocal<>();

    /**
     * 业务方法执行之前，判断当前用户登陆状态，并且封装传递给controller目标请求
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入 CartInterceptor.preHandle()...");
        UserInfoTO userInfoTO = new UserInfoTO();
        HttpSession session = request.getSession();
        MemberRespVO member = (MemberRespVO) session.getAttribute("loginUser");
        if (member != null) {
            log.info("用户已登陆...{}", member.getId());
            // 已登录
            userInfoTO.setUserId(member.getId());
        }
        log.info("用户未登陆...");
        Cookie[] cookies = request.getCookies();
        if (CollectionUtils.isNotEmpty(Arrays.asList(cookies))) {
            for (Cookie cookie : cookies) {
                String userKey = cookie.getName();
                if ("user-key".equals(userKey)) {
                    log.info("用户user-key: {}", userKey);
                    userInfoTO.setUserKey(userKey);
                    userInfoTO.setTmpUser(true);
                }
            }
        }

        // 如果没有临时用户，新建一个
        if (StringUtils.isEmpty(userInfoTO.getUserKey())) {
            String uuid = UUID.randomUUID().toString();
            userInfoTO.setUserKey(uuid);
        }

        // 目标方法执行之前
        log.info("目标方法执行之前，将userInfo保存到userLoginThreadLocal");
        userLoginThreadLocal.set(userInfoTO);

        return true;
    }

    /**
     * 业务方法执行之后，将临时用户信息保存到浏览器的 cookie
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        UserInfoTO userInfoTO = userLoginThreadLocal.get();
        if (!userInfoTO.isTmpUser()) {
            Cookie cookie = new Cookie("user-key", userInfoTO.getUserKey());
            cookie.setDomain("gulimall.com");
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);
        }

    }
}
