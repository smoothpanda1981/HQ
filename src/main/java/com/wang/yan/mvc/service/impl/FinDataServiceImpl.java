package com.wang.yan.mvc.service.impl;

import com.wang.yan.mvc.dao.FinDataDAO;
import com.wang.yan.mvc.model.BuySellBtcUsd;
import com.wang.yan.mvc.service.FinDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FinDataServiceImpl implements FinDataService {

    @Autowired
    private FinDataDAO finDataDAO;

    @Override
    @Transactional
    public List<BuySellBtcUsd> getListBuySellBtcUsd() {
        return finDataDAO.getListBuySellBtcUsd();
    }

    public FinDataDAO getFinDataDAO() {
        return finDataDAO;
    }

    public void setFinDataDAO(FinDataDAO finDataDAO) {
        this.finDataDAO = finDataDAO;
    }
}
