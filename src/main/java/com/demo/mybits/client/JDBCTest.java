package com.demo.mybits.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lichaoshuai
 * Created on 2022-01-24
 * 通过手写jdbc连接的方式，分析一下其缺点
 */
public class JDBCTest {

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 1. 每次都新加载一次类
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 连接信息硬编码
            String url = "jdbc:mysql://127.0.0.1:3306/APPROVE_BIZ";
            String user = "root";
            String password = "rootroot";
            connection = DriverManager.getConnection(url, user, password);
            // 3. sql 和 代码耦合
            String sql = "select * from tb_user where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            // 4. 手动设置参数 ，只能通过下标处理参数
            preparedStatement.setLong(1, 1L);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // 5. 结果集类型需要手动判断
                System.out.println(resultSet.getString("user_name"));
                System.out.println(resultSet.getString("password"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getString("age"));
                System.out.println(resultSet.getString("sex"));
                System.out.println(resultSet.getString("birthday"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6. 资源需要手动关闭
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}
