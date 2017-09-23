package com.wang.yan.mvc.dao.impl;

import com.wang.yan.mvc.dao.BitstampDao;
import com.wang.yan.mvc.model.BitstampProfit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ywang on 25.11.15.
 */

@Repository("bitstampDao")
public class BitstampDaoImpl implements BitstampDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveBitstampProfit(BitstampProfit bitstampProfit) {
        Session session = sessionFactory.getCurrentSession();
        if (session == null) {
            session = sessionFactory.openSession();
        }
        session.save(bitstampProfit);
    }

    @Override
    public BitstampProfit getLastBitstampProfit() {
        BitstampProfit bitstampProfit = new BitstampProfit();
        Session session = sessionFactory.getCurrentSession();
        if (session == null) {
            session = sessionFactory.openSession();
        }
        DetachedCriteria maxId = DetachedCriteria.forClass(BitstampProfit.class).setProjection( Projections.max("id") );
        List<BitstampProfit> bitstampProfitList = session.createCriteria(BitstampProfit.class).add(Property.forName("id").eq(maxId) ).list();
        if (bitstampProfitList.size() > 0) {
         bitstampProfit = bitstampProfitList.get(0);
        }
        return bitstampProfit;
    }
}
