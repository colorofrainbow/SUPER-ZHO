package com.zh.util;

import com.zh.annotation.ForeignKey;
import com.zh.annotation.Key;
import com.zh.configure.XmlOrProperties;

import java.lang.reflect.Field;

public class AnnotationGet {
    public static void main(String args[]){
        try {
            Class c=Class.forName("com.zh.po.user");
            Field []fields=c.getDeclaredFields();
            for (Field f:fields
                 ) {
                boolean exitsK=f.isAnnotationPresent(Key.class);
                boolean exitsFK=f.isAnnotationPresent(ForeignKey.class);
                if (exitsK){
                    Key key=(Key)f.getAnnotation(Key.class);
                    System.out.println(key.value());
                }
                if(exitsFK){
                    ForeignKey foreignKey=(ForeignKey)f.getAnnotation(ForeignKey.class);
                    System.out.println(foreignKey.value());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
