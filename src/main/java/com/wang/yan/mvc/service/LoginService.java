package com.wang.yan.mvc.service;

import com.wang.yan.mvc.model.Login;
import com.wang.yan.mvc.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ywang on 25.11.15.
 */
public interface LoginService {
    Login isValidUser(String username, String password) throws SQLException;

    Login addLogin(String username, String password) throws SQLException;

    Login addNewLogin(String username, String password) throws SQLException;

    Login updateExistingLogin(String current_username, String current_password, String new_username, String new_password) throws SQLException;

    Login deleteExistingLogin(String username, String password) throws SQLException;
}
