package com.example.andy.bookexchange;

/**
 * Created by Andy on 4/1/2017.
 */

public class Book {

    private String Title;
    private String Author;
    private String Desc;
    private String uid;

    public Book() {

    }

    public Book(String title, String author, String desc) {
        Title = title;
        Author = author;
        Desc = desc;
    }

    public String getTitle() {

        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
