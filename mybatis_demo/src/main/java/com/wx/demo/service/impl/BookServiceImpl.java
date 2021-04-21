package com.wx.demo.service.impl;

import com.wx.demo.dao.BookDao;
import com.wx.demo.entity.Book;
import com.wx.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author wangxing
 * @date 2021/4/16 16:37
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;


    @Override
    public List<Book> list(Book book) {
        return bookDao.list(book.getId());
    }

    @Override
    public boolean deleteById(Integer id) {
        return bookDao.deleteById(id);
    }

    @Override
    public void add(Book book) {
        book.setPubDate(new Date());
        book.setBdate(new Date());
        bookDao.add(book);
    }
}
