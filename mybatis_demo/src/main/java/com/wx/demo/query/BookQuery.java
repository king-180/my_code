package com.wx.demo.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wx.demo.base.AbstractPageLimiter;
import com.wx.demo.validator.anno.group.AddGroup;
import com.wx.demo.validator.anno.group.UpdateGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

/**
 * @author wangxing
 * @date 2021/4/25 23:46
 */
public class BookQuery extends AbstractPageLimiter {
    @Null(message = "新增不能带id", groups = {AddGroup.class})
    @NotNull(message = "更新必须带id", groups = {UpdateGroup.class})
    private Integer id;

    private String mainType;

    private String subType;

    @NotBlank(message = "新增或者更新必须带书名", groups = {AddGroup.class, UpdateGroup.class})
    private String bname;

    private String isbn;

    @NotBlank(message = "新增或者更新描述不能为空")
    private String detail;

    private Float oriPrice;

    private Float curPrice;

    private String picture;

    private Integer page;

    private String publisher;

    private String author;

    private String newBook;

    private String hotBook;

    private String saleBook;

    private String recBook;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date bdate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date pubDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMainType() {
        return mainType;
    }

    public void setMainType(String mainType) {
        this.mainType = mainType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Float getOriPrice() {
        return oriPrice;
    }

    public void setOriPrice(Float oriPrice) {
        this.oriPrice = oriPrice;
    }

    public Float getCurPrice() {
        return curPrice;
    }

    public void setCurPrice(Float curPrice) {
        this.curPrice = curPrice;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNewBook() {
        return newBook;
    }

    public void setNewBook(String newBook) {
        this.newBook = newBook;
    }

    public String getHotBook() {
        return hotBook;
    }

    public void setHotBook(String hotBook) {
        this.hotBook = hotBook;
    }

    public String getSaleBook() {
        return saleBook;
    }

    public void setSaleBook(String saleBook) {
        this.saleBook = saleBook;
    }

    public String getRecBook() {
        return recBook;
    }

    public void setRecBook(String recBook) {
        this.recBook = recBook;
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }
}
