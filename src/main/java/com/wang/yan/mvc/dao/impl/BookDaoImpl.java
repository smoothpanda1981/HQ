package com.wang.yan.mvc.dao.impl;

import com.wang.yan.mvc.model.Book;
import com.wang.yan.mvc.dao.BookDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;

/**
 * Created by ywang on 25.11.15.
 */

@Repository("bookDao")
public class BookDaoImpl implements BookDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        if (session == null) {
            session = sessionFactory.openSession();
        }

        Map<String, ClassMetadata> m = sessionFactory.getAllClassMetadata();
        Set<String> talbeNames = m.keySet();

        for (String name : talbeNames) {
            System.out.print(name);
        }

        session.save(book);
    }
}
