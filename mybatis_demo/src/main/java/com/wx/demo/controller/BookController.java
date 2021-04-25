package com.wx.demo.controller;

import com.github.pagehelper.PageHelper;
import com.wx.demo.entity.Book;
import com.wx.demo.query.BookQuery;
import com.wx.demo.service.BookService;
import com.wx.demo.validator.anno.group.AddGroup;
import com.wx.demo.validator.anno.group.UpdateGroup;
import com.wx.demo.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wangxing
 * @date 2021/4/16 16:36
 */
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/book/list")
    public ResultVO<List<Book>> list(@RequestBody Book book) {
        List<Book> bookList = bookService.list(book);
        return new ResultVO<>(bookList);
    }

    @RequestMapping("/book/list2")
    public ResultVO<List<Book>> list2(@RequestBody BookQuery query) {
        PageHelper.startPage(query.getPageNo(), query.getPageSize());
        Book book = new Book();
        BeanUtils.copyProperties(query, book);
        List<Book> bookList = bookService.list(book);
        return new ResultVO<>(bookList);
    }

    @RequestMapping("/book/add")
    public ResultVO<Void> add(@Validated(value = AddGroup.class) @RequestBody Book book) {
        bookService.add(book);
        return new ResultVO<>();
    }

    @RequestMapping("/book/update")
    public ResultVO<Void> update(@Validated(value = UpdateGroup.class) @RequestBody Book book) {

        return new ResultVO<>();
    }

    @RequestMapping("/book/delete/{id}")
    public ResultVO<Void> delete(@PathVariable("id") Integer id) {
        boolean res = bookService.deleteById(id);
        return new ResultVO<>();
    }

    @RequestMapping("/book/delete")
    public ResultVO<Void> delete2(@RequestParam("id") Integer id) {
        boolean res = bookService.deleteById(id);
        return new ResultVO<>();
    }

}
