package com.wang.yan.mvc.service.impl;

import com.wang.yan.mvc.dao.SignupDao;
import com.wang.yan.mvc.model.Signup;
import com.wang.yan.mvc.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * Created by ywang on 25.11.15.
 */

@Service("signupService")
@Transactional
public class SignupServiceImpl implements SignupService {

    @Autowired
    private SignupDao signupDao;


    @Override
    public Signup addNewSignup(Signup signup) throws SQLException {
        return signupDao.addNewSignup(signup);
    }

    @Override
    public Signup updateExistingSignup(Signup singup) throws SQLException {
        return signupDao.updateExistingSignup(singup);
    }

    @Override
    public Signup deleteExistingSignup(Integer signupId) throws SQLException {
        return signupDao.deleteExistingSignup(signupId);
    }
}
