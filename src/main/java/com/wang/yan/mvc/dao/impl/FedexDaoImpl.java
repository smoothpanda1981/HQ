package com.wang.yan.mvc.dao.impl;

import com.wang.yan.mvc.dao.FedexDao;
import com.wang.yan.mvc.dao.LoginDao;
import com.wang.yan.mvc.model.Fedex;
import com.wang.yan.mvc.model.Login;
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

@Repository("fedexDao")
public class FedexDaoImpl implements FedexDao {

    private static final Logger logger = Logger.getLogger(FedexDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Fedex> getListFedex() throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        if (session == null) {
            session = sessionFactory.openSession();
        }
        List<Fedex> fedexList = session.createCriteria(Fedex.class).list();
        return fedexList;
    }

    @Override
    public void addFedex(Fedex fedex) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        if (session == null) {
            session = sessionFactory.openSession();
        }
        session.save(fedex);
    }
}
