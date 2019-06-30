package com.zh.configure;

import com.zh.info.ConfigurationInfo;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.Document;

import java.util.Iterator;

public class XMLReader {
    private static ConfigurationInfo configurationInfo;
    public static String src;
    public static Document d;
    static {
        //src="C:\\Users\\Shuang\\Desktop\\play\\KuangJia\\JieYa\\orm4.0\\src\\configure.xml";
        try {
            //d=new SAXReader().read(src);
            d=new SAXReader().read(Thread.currentThread().getContextClassLoader().getResourceAsStream("configure.xml"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        configurationInfo=new ConfigurationInfo();
        //根节点
        Element xmlRoot=d.getRootElement();
        //元素遍历器
        Iterator eleL=xmlRoot.elementIterator();
        /*
        * 遍历所有的元素
        * 得到元素名为name和value的字符串
        * 然后判断name的值
        * 赋相应的值给对象
        * */
        while(eleL.hasNext()){
            //元素
            Element e=(Element) eleL.next();
            //name
            Attribute attrn=e.attribute("name");
            String name=attrn.getValue();
            //value
            Attribute attrv=e.attribute("value");
            String value=attrv.getValue();
            switch (name){
                    case "driver":configurationInfo.setDriver(value); break;
                    case "url":configurationInfo.setUrl(value);break;
                    case "username":configurationInfo.setUsername(value);break;
                    case "password":configurationInfo.setPassword(value);break;
                    case "dbType":configurationInfo.setDbType(value);break;
                    case "srcAddress":configurationInfo.setSrcAddress(value);break;
                    case "poPackageName":configurationInfo.setPoPackageName(value);break;
                }
        }
    }
    public static ConfigurationInfo getConf(){
        return configurationInfo;
    }

}
