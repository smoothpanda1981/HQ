package com.wang.yan.mvc.dao.impl;

import com.wang.yan.mvc.dao.FinDataDAO;
import com.wang.yan.mvc.model.BuySellBtcUsd;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class FinDataDAOImpl implements FinDataDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<BuySellBtcUsd> getListBuySellBtcUsd() {
        Session session = sessionFactory.getCurrentSession();
        return null;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
