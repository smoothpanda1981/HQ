package com.wang.yan.mvc.service;

import com.wang.yan.mvc.model.BitstampProfit;

/**
 * Created by ywang on 25.11.15.
 */
public interface BitstampService {

    void saveBitstampProfit(BitstampProfit bitstampProfit);

    BitstampProfit getLastBitstampProfit();
}
