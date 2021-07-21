package com.wx.demo.auth.controller;

import com.wx.demo.auth.enums.BizCodeEnums;
import com.wx.demo.auth.constant.AuthConstant;
import com.wx.demo.auth.vo.UserLoginVO;
import com.wx.demo.auth.vo.UserRegistVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author wangxing
 * @date 2021/7/19 19:15
 */
@Controller
public class LoginController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/login.html")
    public String loginPage(HttpSession session) {
        Object loginUser = session.getAttribute(AuthConstant.LOGIN_USER);
        if (loginUser != null) {
            return "redirect:http://gulimall.com";
        }
        return "login";
    }
//
//    @GetMapping("/reg.html")
//    public String regPage() {
//        return "reg";
//    }

    @ResponseBody
    @GetMapping("/sms/sendCode")
    public String sendCode(@RequestParam("phone") String phone) {
        // 防止同一个手机号在 60s 内重复发验证码
        String redisCode = redisTemplate.opsForValue().get(AuthConstant.SMS_CODE_CACHE_PREFIX + phone);
        if (!StringUtils.isEmpty(redisCode)) {
            String[] codes = redisCode.split("_");
            long lastTime = Long.parseLong(codes[1]);
            if (System.currentTimeMillis() - lastTime < 60 * 1000) {
                throw new RuntimeException(BizCodeEnums.SMS_CODE_EXCEPTION.getMsg());
            }
        }
        // 接口防刷，验证码再次校验,临时存到 redis
        String code = UUID.randomUUID().toString().replace("-", "").substring(0, 5) + "_" + System.currentTimeMillis();

        redisTemplate.opsForValue().set(AuthConstant.SMS_CODE_CACHE_PREFIX + phone, code, 10, TimeUnit.MINUTES);

        // thirdPartFeignService.sendCode(phone, code);

        return "success";
    }

    /**
     * 重定向携带数据，利用session原理，只要跳到下一个页面取出数据后，session里的数据就会被删除
     *
     * @param userRegistVO 注册表单数据
     * @param result       校验结果绑定
     * @param attributes   重定向可以携带数据
     * @return
     */
    @PostMapping("/regist")
    public String regist(@Valid UserRegistVO userRegistVO, BindingResult result, RedirectAttributes attributes/*Model model*/) {
        // 用 Model 用重定向方式不能携带域中的数据
        Map<String, String> errorMap = new HashMap<>();
        if (result.hasErrors()) {
            result.getFieldErrors().forEach(fieldError -> errorMap.put(fieldError.getField(), fieldError.getDefaultMessage()));
            attributes.addFlashAttribute("errors", errorMap);
            // 校验出错，返回到注册页
            return "redirect:http://127.0.0.1:8027/reg.html";
        }

        // 校验验证码
        String phone = userRegistVO.getPhone();
        String code = userRegistVO.getCode();

        String codeFromRedis = redisTemplate.opsForValue().get(AuthConstant.SMS_CODE_CACHE_PREFIX + phone);

        if (!StringUtils.isEmpty(codeFromRedis)) {
            if (code.equals(codeFromRedis.split("_")[0])) {
                // 验证码正确,删除验证码
                redisTemplate.delete(AuthConstant.SMS_CODE_CACHE_PREFIX + phone);
                // 开始注册，用户信息存到数据库

                // 注册成功 重定向到首页
                return "redirect:/login.html";
            } else {
                errorMap.put("code", "验证码错误");
                attributes.addFlashAttribute("errors", errorMap);
                // 校验出错，返回到注册页
                return "redirect:http://127.0.0.1:8027/reg.html";
            }
        } else {
            errorMap.put("code", "验证码错误");
            attributes.addFlashAttribute("errors", errorMap);
            // 校验出错，返回到注册页
            return "redirect:http://127.0.0.1:8027/reg.html";
        }

    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginVO vo, RedirectAttributes attributes) {
        // 调用远程服务登陆
        // MemberEntity member = memberFeignService.login(vo);
        // 登陆失败跳转到登录页，成功跳转到主页

        return "redirect:http://127.0.0.1:8027/index.html";
    }

}
