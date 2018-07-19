package com.stu.dao;

import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author zxg
 * @date 2018/7/16
 */
public interface BaseDao {
    /**
     * 创建客户端
     * @return
     */
    Jedis getJedis();

    Connection getCon() throws ClassNotFoundException, SQLException;
}
