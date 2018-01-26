package com.wang.yan.mvc.dao.impl;

import com.wang.yan.mvc.dao.SignupDao;
import com.wang.yan.mvc.dao.TickerHourDao;
import com.wang.yan.mvc.model.Login;
import com.wang.yan.mvc.model.Signup;
import com.wang.yan.mvc.model.bitstamp.TickerHour;
import com.wang.yan.mvc.utils.MD5Encryption;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ywang on 25.11.15.
 */

@Repository("TickerHourDao")
public class TickerHourDaoImpl implements TickerHourDao {

    private static final Logger logger = Logger.getLogger(TickerHourDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<TickerHour> getListOfTickerHour() {
        Session session = sessionFactory.getCurrentSession();
        if (session == null) {
            session = sessionFactory.openSession();
        }
        List<TickerHour> listTickerHour = session.createCriteria(TickerHour.class).list();
        if (listTickerHour != null) {
            return listTickerHour;
        } else {
            return null;
        }
    }
}
