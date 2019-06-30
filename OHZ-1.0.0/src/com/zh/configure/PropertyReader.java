package com.zh.configure;

import com.zh.info.ConfigurationInfo;

import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private  static Properties properties;
    private static ConfigurationInfo configurationInfo;
    static {
        //read properties
        try {
            properties=new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        configurationInfo=new ConfigurationInfo();
        configurationInfo.setDbType(properties.getProperty("dbType"));
        configurationInfo.setDriver(properties.getProperty("driver"));
        configurationInfo.setUrl(properties.getProperty("url"));
        configurationInfo.setUsername(properties.getProperty("username"));
        configurationInfo.setPassword(properties.getProperty("password"));
        configurationInfo.setSrcAddress(properties.getProperty("srcAddress"));
        configurationInfo.setPoPackageName(properties.getProperty("poPackageName"));
    }
    public static ConfigurationInfo getConf(){
        return configurationInfo;
    }
}
