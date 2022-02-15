package com.demo.mybits.dao;

import java.util.List;

import com.demo.mybits.model.User;

/**
 * @author lichaoshuai
 * Created on 2022-01-25
 */
public interface UserDao {

    User selectUser(long id);

    List<User> queryAll();

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(long id) ;
}
