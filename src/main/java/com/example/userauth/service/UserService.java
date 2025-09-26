package com.example.userauth.service;

import com.example.userauth.dao.UserDao;
import com.example.userauth.model.User;

public class UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void register(User user) {
        userDao.save(user);
    }

    public User login(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }
}
