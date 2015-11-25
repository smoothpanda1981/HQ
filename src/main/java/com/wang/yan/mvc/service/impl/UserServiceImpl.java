package com.wang.yan.mvc.service.impl;

import com.wang.yan.mvc.dao.User;
import com.wang.yan.mvc.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by ywang on 25.11.15.
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private static List<User> users;

    static{
        users= populateDummyUsers();
    }

    public List<User> findAllUsers() {
        return users;
    }

    public User findById(long id) {
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public User findByName(String name) {
        for(User user : users){
            if(user.getName().equalsIgnoreCase(name)){
                return user;
            }
        }
        return null;
    }

    public void saveUser(User user) {
        users.add(user);
    }

    public void updateUser(User user) {
        int index = users.indexOf(user);
        users.set(index, user);
    }

    public void deleteUserById(long id) {

        for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            }
        }
    }

    public boolean isUserExist(User user) {
        return findByName(user.getName())!=null;
    }

    private static List<User> populateDummyUsers(){
        List<User> users = new ArrayList<User>();
        User user1 = new User(1, "Sam", 30, 70000);
        User user2 = new User(2, "Tom",40, 50000);
        User user3 = new User(3, "Jerome",45, 30000);
        User user4 = new User(4, "Silvia",50, 40000);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        return users;
    }

    public void deleteAllUsers() {
        users.clear();
    }
}
