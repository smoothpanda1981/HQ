package com.wang.yan.mvc.service.impl;

import com.wang.yan.mvc.dao.LoginDao;
import com.wang.yan.mvc.model.Login;
import com.wang.yan.mvc.model.User;
import com.wang.yan.mvc.service.LoginService;
import com.wang.yan.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ywang on 25.11.15.
 */

@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public Login isValidUser(String username, String password) throws SQLException {
        return loginDao.isValidUser(username, password);
    }

    @Override
    public Login addNewLogin(String username, String password) throws SQLException {
        return loginDao.addNewLogin(username, password);
    }

    @Override
    public Login updateExistingLogin(String current_username, String current_password, String new_username, String new_password) throws SQLException {
        return loginDao.updateExistingLogin(current_username, current_password, new_username, new_password);
    }

    @Override
    public Login deleteExistingLogin(String username, String password) throws SQLException {
        return loginDao.deleteExistingLogin(username, password);
    }
}
