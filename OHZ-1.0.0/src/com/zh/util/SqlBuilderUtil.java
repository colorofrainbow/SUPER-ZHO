package com.zh.util;

import com.zh.configure.TableContext;
import com.zh.info.ColumnInfo;
import com.zh.info.TableInfo;
import javafx.scene.control.Tab;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
* 创建sql语句
*
* */

public class SqlBuilderUtil {
     static Map<String,TableInfo> map=TableContext.tables;
    public static String createSelectSql(String tablename){
        StringBuilder select=new StringBuilder();
        select.append("select*from "+tablename);
        return select.toString();
    }
    public static String createSelectOne(String tablename,int key){
        TableInfo tableInfo=map.get(tablename);
        ColumnInfo prikey=tableInfo.getOnlyPriKey();
        StringBuilder selectone=new StringBuilder();
        selectone.append("select*from "+tablename).append(" where 1=1");
        selectone.append(" and "+prikey.getName()+" = "+key);
        return selectone.toString();
    }
    public static String createDeleteOne(String tablename,int key){
        TableInfo tableInfo=map.get(tablename);
        ColumnInfo prikey=tableInfo.getOnlyPriKey();
        StringBuilder deleteone=new StringBuilder();
        deleteone.append("delete from "+tablename).append(" where 1=1");
        deleteone.append(" and "+prikey.getName()+" = "+key);
        return deleteone.toString();
    }
    //创建插入语句
    public static String createInsertOne(Class c,Map map,boolean isKeyAuto) {
        TableInfo tableInfo = (TableInfo) map.get(c.getSimpleName());
        Field[] fields = c.getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        sb.append("insert into " + c.getSimpleName() + "(");
        if (!isKeyAuto) {
            int count = 0;
            for (Field f : fields
                    ) {
                String cname = f.getName();
                if (count == (fields.length - 1)) {
                    sb.append(cname);
                } else {
                    sb.append(cname + ",");
                }
                count += 1;
            }
            sb.append(") values(");
            for (int i = 0; i < fields.length; i++) {
                if (i == (fields.length - 1)) {
                    sb.append(map.get(fields[i].getName()) + ")");
                } else {
                    sb.append(map.get(fields[i].getName()) + ",");
                }
            }
            return sb.toString();
        }else{
            int count = 0;
            for (Field f : fields
                    ) {
                String cname = f.getName();
                if (count == (fields.length - 2)) {
                    sb.append(cname);
                    break;
                } else {
                    sb.append(cname + ",");
                }
                count += 1;
            }
            sb.append(") values(");
            for (int i = 0; i < fields.length-1; i++) {
                if (i == (fields.length - 2)) {
                    sb.append(map.get(fields[i].getName()) + ")");
                } else {
                    sb.append(map.get(fields[i].getName()) + ",");
                }
            }
            return sb.toString();
        }
    }

    public static String createUpdateOne(Class c, Map<String,Object> map, String keyName) {
        TableInfo tableInfo = (TableInfo) map.get(c.getSimpleName());
        Field[] fields = c.getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        sb.append("update " + c.getSimpleName() + " set");
//        if (iskeyAuto) {
            int count = 0;
            for (Field f : fields
                    ) {
                String cname = f.getName();
                if (count == (fields.length - 1)) {
                    sb.append(" "+cname+"="+map.get(f.getName())+" where 1=1 and ");
                    sb.append(keyName+"="+map.get(keyName)+";");
                } else {
                    sb.append(" "+cname+"="+map.get(f.getName())+",");
                }
                count += 1;
            }
//            sb.append(") values(");
//            for (int i = 0; i < fields.length; i++) {
//                if (i == (fields.length - 1)) {
//                    sb.append(map.get(fields[i].getName()) + ")");
//                } else {
//                    sb.append(map.get(fields[i].getName()) + ",");
//                }
//            }
            return sb.toString();
      // }
// else{
//            int count = 0;
//            for (Field f : fields
//                    ) {
//                String cname = f.getName();
//                if (count == (fields.length - 2)) {
//                    sb.append(cname);
//                    break;
//                } else {
//                    sb.append(cname + ",");
//                }
//                count += 1;
//            }
//            sb.append(") values(");
//            for (int i = 0; i < fields.length-1; i++) {
//                if (i == (fields.length - 2)) {
//                    sb.append(map.get(fields[i].getName()) + ")");
//                } else {
//                    sb.append(map.get(fields[i].getName()) + ",");
//                }
//            }
//            return sb.toString();
//        }
    }
}
