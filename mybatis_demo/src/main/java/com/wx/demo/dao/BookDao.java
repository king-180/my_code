package com.wx.demo.dao;

import com.wx.demo.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wangxing
 * @date 2021/4/16 16:04
 */
@Mapper
public interface BookDao {

    List<Book> list(Integer id);

    boolean deleteById(Integer id);

    void add(Book book);
}
