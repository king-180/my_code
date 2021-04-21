package com.wx.demo.dao;

import com.wx.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangxing
 * @date 2021/4/20 15:30
 */
public interface UserDao {

    @Select("select * from t_user")
    List<UserEntity> list();


}
