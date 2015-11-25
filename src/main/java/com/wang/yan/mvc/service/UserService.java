package com.wang.yan.mvc.service;

import com.wang.yan.mvc.dao.User;

import java.util.List;

/**
 * Created by ywang on 25.11.15.
 */
public interface UserService {
    User findById(long id);

    User findByName(String name);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(long id);

    List<User> findAllUsers();

    void deleteAllUsers();

    public boolean isUserExist(User user);
}
