package com.wang.yan.mvc.service.impl;

import com.wang.yan.mvc.dao.BitstampDao;
import com.wang.yan.mvc.model.BitstampProfit;
import com.wang.yan.mvc.service.BitstampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ywang on 25.11.15.
 */

@Service("bitstampService")
@Transactional
public class BitstampServiceImpl implements BitstampService {

    @Autowired
    private BitstampDao bitstampDao;

    @Override
    public void saveBitstampProfit(BitstampProfit bitstampProfit) {
        bitstampDao.saveBitstampProfit(bitstampProfit);
    }

    @Override
    public BitstampProfit getLastBitstampProfit() {
        return bitstampDao.getLastBitstampProfit();
    }


}
