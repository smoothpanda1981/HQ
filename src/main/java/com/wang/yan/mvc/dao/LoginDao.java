package com.wang.yan.mvc.dao;

import com.wang.yan.mvc.model.Book;
import com.wang.yan.mvc.model.Login;

import java.sql.SQLException;

/**
 * Created by ywang on 25.11.15.
 */
public interface LoginDao {
    public Login isValidUser(String username, String password) throws SQLException;
}
