package com.wang.yan.mvc.dao;

import com.wang.yan.mvc.model.Signup;
import com.wang.yan.mvc.model.bitstamp.TickerHour;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ywang on 25.11.15.
 */
public interface TickerHourDao {
    List<TickerHour> getListOfTickerHour();
}
