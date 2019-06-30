package com.zh.util;

import com.zh.configure.dbManger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
* 用于执行sql
* 返回结果集
* */

public class MySQLHandler {
     static Connection connection=dbManger.getConnection();
    public static ResultSet excuteQuery(String sql){
        ResultSet rs=null;
        try {
            Statement statement=connection.createStatement();
            rs=statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    public static void excute(String sql){
        try {
            Statement statement=connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
