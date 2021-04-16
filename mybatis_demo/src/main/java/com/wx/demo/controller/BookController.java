package com.wx.demo.controller;

import com.wx.demo.entity.Book;
import com.wx.demo.service.BookService;
import com.wx.demo.validator.anno.group.AddGroup;
import com.wx.demo.validator.anno.group.UpdateGroup;
import com.wx.demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public ResultVO<Void> add(@Validated(value = AddGroup.class) @RequestBody Book book) {

        return new ResultVO<>();
    }

    public ResultVO<Void> update(@Validated(value = UpdateGroup.class) @RequestBody Book book) {

        return new ResultVO<>();
    }

}
