package com.wang.yan.mvc.service.impl;

import com.wang.yan.mvc.model.Book;
import com.wang.yan.mvc.dao.BookDao;
import com.wang.yan.mvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ywang on 25.11.15.
 */

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public void saveBook(Book book) {
        bookDao.saveBook(book);
    }
}
