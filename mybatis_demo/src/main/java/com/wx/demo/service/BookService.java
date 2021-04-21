package com.wx.demo.service;

import com.wx.demo.entity.Book;

import java.util.List;

/**
 * @author wangxing
 * @date 2021/4/16 16:36
 */
public interface BookService {

    List<Book> list(Book book);

    boolean deleteById(Integer id);
}
