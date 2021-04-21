package com.wx.demo.service;

import com.wx.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangxing
 * @date 2021/4/20 15:29
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;


}
