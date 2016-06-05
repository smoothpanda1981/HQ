package com.wang.yan.mvc.dao.impl;

import com.wang.yan.mvc.dao.LoginDao;
import com.wang.yan.mvc.dao.SignupDao;
import com.wang.yan.mvc.model.Login;
import com.wang.yan.mvc.model.Signup;
import com.wang.yan.mvc.utils.MD5Encryption;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * Created by ywang on 25.11.15.
 */

@Repository("signupDao")
public class SignupDaoImpl implements SignupDao {

    private static final Logger logger = Logger.getLogger(SignupDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Signup addNewSignup(Signup signup) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        if (session == null) {
            session = sessionFactory.openSession();
        }
        signup.setPassword("");
        signup.setPassword_confirmation("");

        session.save(signup);

        return signup;
    }

    @Override
    public Signup updateExistingSignup(Signup singup) throws SQLException {
        return null;
    }

    @Override
    public Signup deleteExistingSignup(Integer signupId) throws SQLException {
        return null;
    }
}
