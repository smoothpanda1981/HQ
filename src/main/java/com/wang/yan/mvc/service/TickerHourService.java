package com.wang.yan.mvc.service;

import com.wang.yan.mvc.model.bitstamp.TickerHour;

import java.util.List;

/**
 * Created by ywang on 25.11.15.
 */
public interface TickerHourService {
    List<TickerHour> getListOfTickerHour(String cryptoCurrency);
}
