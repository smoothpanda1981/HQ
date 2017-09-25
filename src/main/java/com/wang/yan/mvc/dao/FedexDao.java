package com.wang.yan.mvc.dao;

import com.wang.yan.mvc.model.Fedex;
import com.wang.yan.mvc.model.Login;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ywang on 25.11.15.
 */
public interface FedexDao {

    List<Fedex> getListFedex() throws SQLException;

    void addFedex(Fedex fedex) throws SQLException;
}
