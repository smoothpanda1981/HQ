package com.wang.yan.mvc.dao;

import com.wang.yan.mvc.model.Fedex;
import com.wang.yan.mvc.model.Login;

import java.sql.SQLException;

/**
 * Created by ywang on 25.11.15.
 */
public interface FedexDao {

    void addFedex(Fedex fedex) throws SQLException;
}
