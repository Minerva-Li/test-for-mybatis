package com.demo.mybits.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.demo.mybits.dao.impl.UserDaoImpl;
import com.demo.mybits.model.User;
import com.vdurmont.emoji.EmojiManager;

/**
 * @author lichaoshuai
 * Created on 2022-01-25
 */
public class MybatisWithDaoClient {

    private static SqlSession sqlSession;

    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        sqlSession = sqlSessionFactory.openSession();
    }

    public static void main(String[] args) throws IOException {
        String str = "\uD83D\uDD25\n";
        if (EmojiManager.containsEmoji(str)) {
            System.out.println("this is emoji");
        }

//        UserDaoImpl userDao = new UserDaoImpl(sqlSession);
////        System.out.println(userDao.queryAll());
//
//        System.out.println(userDao.selectUser(18));
//
//        System.out.println(userDao.queryAll());

//        User user = new User();
//        user.setAge(10);
//        user.setBirthday(new Date());
//        user.setUser_name("james");
//        user.setPassword("that");
//        user.setSex(1);
//        userDao.insertUser(user);

        //                System.out.println(userDao.queryAll());

//        user.setPassword("this");
//        userDao.updateUser(user);

        //        System.out.println(userDao.queryAll());

        //        userDao.deleteUser(user.getId());
        //        System.out.println(userDao.queryAll());

    }

}
