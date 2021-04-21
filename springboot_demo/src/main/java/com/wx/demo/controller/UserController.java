package com.wx.demo.controller;

import com.wx.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangxing
 * @date 2021/4/20 15:28
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;


}
