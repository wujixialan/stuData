package com.stu.dao.impl;

import com.stu.dao.BaseDao;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author kevin zhao
 * @date 2018/7/16
 */
public class BaseDaoImpl implements BaseDao {
    private String DB_URL = "jdbc:mysql://localhost:3307/test?serverTimezone=Hongkong&characterEncoding=UTF8";
    private String USER = "root";
    private String PASS = "031209";
    /**
     * 创建客户端
     * @return
     */
    @Override
    public Jedis getJedis() {
        return new Jedis("123.207.55.72", 6379);
    }

    @Override
    public Connection getCon() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");
            return DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
