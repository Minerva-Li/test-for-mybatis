package com.demo.mybits.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.demo.mybits.dao.UserDao;
import com.demo.mybits.model.User;

/**
 * @author lichaoshuai
 * Created on 2022-01-25
 */
public class UserDaoImpl implements UserDao {

    private SqlSession session;

    public UserDaoImpl(SqlSession session) {
        this.session = session;
    }

    @Override
    public User selectUser(long id) {
        return this.session.selectOne("UserMapper.selectUser", id);
    }

    @Override
    public List<User> queryAll() {
        return this.session.selectList("UserMapper.selectAll");
    }

    @Override
    public void insertUser(User user) {
        this.session.insert("UserMapper.insertUser", user);
        this.session.commit();
    }

    @Override
    public void updateUser(User user) {
        this.session.update("UserMapper.updateUser", user);
        this.session.commit();
    }

    @Override
    public void deleteUser(long id) {
        this.session.delete("UserMapper.deleteUser", id);
        this.session.commit();
    }
}
