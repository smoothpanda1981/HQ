package com.wang.yan.mvc.dao;

import com.wang.yan.mvc.model.BitstampProfit;

/**
 * Created by ywang on 25.11.15.
 */
public interface BitstampDao {

    void saveBitstampProfit(BitstampProfit bitstampProfit);

    BitstampProfit getLastBitstampProfit();
}
