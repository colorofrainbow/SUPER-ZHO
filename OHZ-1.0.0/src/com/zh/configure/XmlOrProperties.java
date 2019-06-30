package com.zh.configure;

import com.zh.info.ConfigurationInfo;

import java.io.File;

public class XmlOrProperties {
    private static ConfigurationInfo confX;
    private static ConfigurationInfo confP;
    static {
        confX=XMLReader.getConf();
        confP=PropertyReader.getConf();
    }
    public static ConfigurationInfo getConf(){
        if(confX!=null){
            return confX;
        }else if(confP!=null){
            return PropertyReader.getConf();
        }else {
            return null;
        }
    }
}
