package com.wang.yan.mvc.dao.impl;

import com.wang.yan.mvc.dao.BookDao;
import com.wang.yan.mvc.dao.LoginDao;
import com.wang.yan.mvc.model.Book;
import com.wang.yan.mvc.model.Login;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ywang on 25.11.15.
 */

@Repository("loginDao")
public class LoginDaoImpl implements LoginDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Login isValidUser(String username, String password) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        if (session == null) {
            session = sessionFactory.openSession();
        }
        Login login = (Login) session.createCriteria(Login.class).add(Restrictions.eq("username", username)).add(Restrictions.eq("password", password)).uniqueResult();
        if (login != null) {
            return login;
        } else {
            return null;
        }
    }
}
