package com.wang.yan.mvc.service;

import com.wang.yan.mvc.model.Signup;

import java.sql.SQLException;

/**
 * Created by ywang on 25.11.15.
 */
public interface SignupService {
    Signup addNewSignup(Signup signup) throws SQLException;

    Signup updateExistingSignup(Signup singup) throws SQLException;

    Signup deleteExistingSignup(Integer signupId) throws SQLException;
}
