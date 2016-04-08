package com.wang.yan.mvc.service;

import com.wang.yan.mvc.model.Login;
import com.wang.yan.mvc.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ywang on 25.11.15.
 */
public interface LoginService {
    public Login isValidUser(String username, String password) throws SQLException;
}
