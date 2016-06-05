package com.wang.yan.mvc.dao;

import com.wang.yan.mvc.model.Book;
import com.wang.yan.mvc.model.Login;

import java.sql.SQLException;

/**
 * Created by ywang on 25.11.15.
 */
public interface LoginDao {
    Login isValidUser(String username, String password) throws SQLException;

    Login addLogin(String username, String password) throws SQLException;

    Login addNewLogin(String username, String password) throws SQLException;

    Login updateExistingLogin(String current_username, String current_password, String new_username, String new_password) throws SQLException;

    Login deleteExistingLogin(String username, String password) throws SQLException;
}
