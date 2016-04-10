package com.wang.yan.mvc.dao.impl;

import com.wang.yan.mvc.DBLoginController;
import com.wang.yan.mvc.dao.BookDao;
import com.wang.yan.mvc.dao.LoginDao;
import com.wang.yan.mvc.model.Book;
import com.wang.yan.mvc.model.Login;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ywang on 25.11.15.
 */

@Repository("loginDao")
public class LoginDaoImpl implements LoginDao {

    private static final Logger logger = Logger.getLogger(LoginDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Login isValidUser(String username, String password) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        if (session == null) {
            session = sessionFactory.openSession();
        }
        Login login = (Login) session.createCriteria(Login.class).add(Restrictions.eq("username", username)).add(Restrictions.eq("password", password)).uniqueResult();
        if (login != null) {
            return login;
        } else {
            return null;
        }
    }

    @Override
    public Login addNewLogin(String username, String password) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        if (session == null) {
            session = sessionFactory.openSession();
        }
        Login login = (Login) session.createCriteria(Login.class).add(Restrictions.eq("username", username)).uniqueResult();

        Login newLogin = new Login();

        if (login != null) {
            newLogin.setUsername("Login '" + username + "' already exists!");
        } else {
            Login logingToCreate = new Login();
            logingToCreate.setUsername(username);
            logingToCreate.setPassword(password);
            session.save(logingToCreate);

            newLogin.setUsername("Login '" + username + "' has been created successfully!");
        }
        return newLogin;
    }

    @Override
    public Login updateExistingLogin(String current_username, String current_password, String new_username, String new_password) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        if (session == null) {
            session = sessionFactory.openSession();
        }
        Login updateLogin = new Login();

        Login login = (Login) session.createCriteria(Login.class).add(Restrictions.eq("username", current_username)).uniqueResult();
        if (login == null) {
            logger.debug("login == null");
            updateLogin.setUsername("Login '" + current_username + "' doesn't exists!");
        } else {
            logger.debug("login != null");
            Login login2 = isValidUser(current_username, current_password);
            if (login2 == null) {
                logger.debug("login2 == null");
                updateLogin.setUsername("Login '" + current_username + "' does exist, but password is wrong!");
            } else {
                logger.debug("login2 != null");
                Login login3 = (Login) session.createCriteria(Login.class).add(Restrictions.eq("username", new_username)).uniqueResult();
                if (login3 != null) {
                    logger.debug("login3 != null");
                    updateLogin.setUsername("New Login '" + new_username + "' already exists, please choose a new username!");
                } else {
                    logger.debug("login3 == null");
                    Login toUpdateLogin = (Login) session.load(Login.class, login2.getId());
                    toUpdateLogin.setUsername(new_username);
                    toUpdateLogin.setPassword(new_password);
                    session.update(toUpdateLogin);
                    updateLogin.setUsername("New Login '" + current_username + "' has been updated successfully!");
                }
            }
        }
        return updateLogin;
    }

    @Override
    public Login deleteExistingLogin(String username, String password) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        if (session == null) {
            session = sessionFactory.openSession();
        }
        Login deleteLogin = new Login();

        Login login = (Login) session.createCriteria(Login.class).add(Restrictions.eq("username", username)).uniqueResult();
        if (login == null) {
            logger.debug("login == null");
            deleteLogin.setUsername("Login '" + username + "' doesn't exists!");
        } else {
            logger.debug("login != null");
            Login login2 = isValidUser(username, password);
            if (login2 == null) {
                logger.debug("login2 == null");
                deleteLogin.setUsername("Login '" + username + "' does exist, but password is wrong!");
            } else {
                Login toDeleteLogin = (Login) session.load(Login.class, login2.getId());
                session.delete(toDeleteLogin);
                deleteLogin.setUsername("Login '" + username + "' has been deleted successfully!");
            }
        }
        return deleteLogin;
    }
}
