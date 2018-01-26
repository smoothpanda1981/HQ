package com.wang.yan.mvc.service.impl;

import com.wang.yan.mvc.dao.SignupDao;
import com.wang.yan.mvc.dao.TickerHourDao;
import com.wang.yan.mvc.model.Signup;
import com.wang.yan.mvc.model.bitstamp.TickerHour;
import com.wang.yan.mvc.service.SignupService;
import com.wang.yan.mvc.service.TickerHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ywang on 25.11.15.
 */

@Service("tickerHourService")
@Transactional
public class TickerHourServiceImpl implements TickerHourService {

    @Autowired
    private TickerHourDao tickerHourDao;


    @Override
    public List<TickerHour> getListOfTickerHour() {
        return tickerHourDao.getListOfTickerHour();
    }
}
