package com.wang.yan.mvc.dao.impl;

import com.wang.yan.mvc.dao.TickerHourDao;
import com.wang.yan.mvc.model.bitstamp.TickerHour;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ywang on 25.11.15.
 */

@Repository("TickerHourDao")
public class TickerHourDaoImpl implements TickerHourDao {

    private static final Logger logger = Logger.getLogger(TickerHourDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<TickerHour> getListOfTickerHour(String cryptoCurrency) {
        Session session = sessionFactory.getCurrentSession();
        if (session == null) {
            session = sessionFactory.openSession();
        }
        List<TickerHour> listTickerHour = session.createCriteria(TickerHour.class).list();
        List<TickerHour> listTickerHourWithRightCryptoCurrency = listTickerHour.stream().filter(th -> th.getCryptoCurrency().equals(cryptoCurrency)).collect(Collectors.toList());


        if (listTickerHourWithRightCryptoCurrency != null) {
            return listTickerHourWithRightCryptoCurrency;
        } else {
            return null;
        }
    }
}