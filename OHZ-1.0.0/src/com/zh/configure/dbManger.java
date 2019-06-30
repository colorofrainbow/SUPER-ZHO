package com.zh.configure;

import com.zh.info.ConfigurationInfo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class  dbManger {
    private static  DatabaseMetaData  databaseMetaData;
    public static DatabaseMetaData getMetaData(){
        try {
             databaseMetaData=getConnection().getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return databaseMetaData;
    }
    public static Connection getConnection(){
        ConfigurationInfo conf=XmlOrProperties.getConf();
        Connection connection=null;
        try {
            Class.forName(conf.getDriver());
            connection=DriverManager.getConnection(conf.getUrl(),conf.getUsername(),conf.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
