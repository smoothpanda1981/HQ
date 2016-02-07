package com.wang.yan.mvc.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ywang on 20/12/15.
 */
@Entity
@Table(name="BOOK")
public class Book {
    private Long id;
    private String name;
    private String code;
    private String price;
    private String authors;
    private String isbn;
    private String publisher;
    private Date publishedOn;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    @Column(length = 15, nullable = false)
    public String getCode() {
        return code;
    }

    @Column(length = 10)
    public String getPrice() {
        return price;
    }

    @Column(nullable = false)
    public String getAuthors() {
        return authors;
    }

    @Column
    public String getIsbn() {
        return isbn;
    }

    @Column
    public String getPublisher() {
        return publisher;
    }

    @Column(name = "published_date")
    public Date getPublishedOn() {
        return publishedOn;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishedOn(Date publishedOn) {
        this.publishedOn = publishedOn;
    }
}
