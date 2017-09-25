package com.wang.yan.mvc.service.impl;

import com.wang.yan.mvc.dao.FedexDao;
import com.wang.yan.mvc.model.Fedex;
import com.wang.yan.mvc.service.FedexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * Created by ywang on 25.11.15.
 */

@Service("fedexService")
@Transactional
public class FedexServiceImpl implements FedexService {

    @Autowired
    private FedexDao fedexDao;


    @Override
    public void addFedex(Fedex fedex) throws SQLException {
        fedexDao.addFedex(fedex);
    }
}