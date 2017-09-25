package com.wang.yan.mvc.service.impl;

import com.wang.yan.mvc.dao.FedexDao;
import com.wang.yan.mvc.model.Fedex;
import com.wang.yan.mvc.service.FedexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ywang on 25.11.15.
 */

@Service("fedexService")
@Transactional
public class FedexServiceImpl implements FedexService {

    @Autowired
    private FedexDao fedexDao;


    @Override
    public List<Fedex> getListFedex() throws SQLException {
        return fedexDao.getListFedex();
    }

    @Override
    public void addFedex(Fedex fedex) throws SQLException {
        fedexDao.addFedex(fedex);
    }
}