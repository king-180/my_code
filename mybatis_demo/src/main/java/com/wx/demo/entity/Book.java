package com.wx.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable{
    private Integer id;

    private String mainType;

    private String subType;

    private String bname;

    private String isbn;

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

    private Date bdate;

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
        this.mainType = mainType == null ? null : mainType.trim();
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType == null ? null : subType.trim();
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname == null ? null : bname.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
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
        this.picture = picture == null ? null : picture.trim();
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
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getNewBook() {
        return newBook;
    }

    public void setNewBook(String newBook) {
        this.newBook = newBook == null ? null : newBook.trim();
    }

    public String getHotBook() {
        return hotBook;
    }

    public void setHotBook(String hotBook) {
        this.hotBook = hotBook == null ? null : hotBook.trim();
    }

    public String getSaleBook() {
        return saleBook;
    }

    public void setSaleBook(String saleBook) {
        this.saleBook = saleBook == null ? null : saleBook.trim();
    }

    public String getRecBook() {
        return recBook;
    }

    public void setRecBook(String recBook) {
        this.recBook = recBook == null ? null : recBook.trim();
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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", mainType='" + mainType + '\'' +
                ", subType='" + subType + '\'' +
                ", bname='" + bname + '\'' +
                ", isbn='" + isbn + '\'' +
                ", detail='" + detail + '\'' +
                ", oriPrice=" + oriPrice +
                ", curPrice=" + curPrice +
                ", picture='" + picture + '\'' +
                ", page=" + page +
                ", publisher='" + publisher + '\'' +
                ", author='" + author + '\'' +
                ", newBook='" + newBook + '\'' +
                ", hotBook='" + hotBook + '\'' +
                ", saleBook='" + saleBook + '\'' +
                ", recBook='" + recBook + '\'' +
                ", bdate=" + bdate +
                ", pubDate=" + pubDate +
                '}';
    }
}