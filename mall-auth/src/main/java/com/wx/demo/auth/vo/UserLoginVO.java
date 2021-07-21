package com.wx.demo.auth.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author wangxing
 * @date 2021/7/19 21:00
 */
@Data
public class UserLoginVO {

    @NotBlank(message = "手机号必须提交")
    @Pattern(regexp = "^[1]([3-9])[0-9]{9}$/", message = "手机号格式不正确")
    private String phone;

    @NotBlank(message = "密码必须提交")
    @Length(min = 6, max = 18, message = "密码长度在{min}到{max}之间")
    private String password;

}
