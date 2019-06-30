package com.zh.util;

import com.zh.annotation.Key;
import com.zh.info.TableInfo;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
* 用sqlbuilder创建sql语句
* 用MySQLHandler执行sql语句返回结果集
* 得到这个表的描述获得列数
*
* 执行操作数据库
*
* */
public class DbOperate {
    /*
     * 得到表属性的名字
     * 通过属性名得到类的属性
     * 设置属性的值
     * 把这个属性设置为对象的一个属性
     * 循环
     * 添加到list中
     * 返回
     * */
    public static Object selectOne(Class clazz,int key){
        Object o=null;
        String tablename=clazz.getSimpleName();
        String sql=SqlBuilderUtil.createSelectOne(tablename,key);
        try {
            o=clazz.newInstance();
            ResultSet rs=MySQLHandler.excuteQuery(sql);
            ResultSetMetaData rsmd=rs.getMetaData();
            int count=rsmd.getColumnCount();
            while (rs.next()){
                for(int i=0;i<count;i++){
                    String columnname=rsmd.getColumnName(i+1);
                    Object fo=rs.getObject(columnname);
                    Field field=clazz.getDeclaredField(columnname);
                    field.setAccessible(true);
                    field.set(o,fo);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NoSuchFieldException e){
            e.printStackTrace();
        }
        return o;
    }
    public static void deleteOne(Class c,int key) throws SQLException {
        Object o=null;
        String sql=SqlBuilderUtil.createDeleteOne(c.getSimpleName(),key);
        MySQLHandler.excute(sql);
    }
    public static ResultSet excuteSql(String sql){
        ResultSet rs=MySQLHandler.excuteQuery(sql);
        return rs;
    }
    public static void insert(Object o) throws IllegalAccessException{
        Class c=o.getClass();
        String tablename=c.getSimpleName();
        Map<String,Object> map=new HashMap<String, Object>();
        Field []fields=c.getDeclaredFields();
        boolean iskeyAuto=false;
        for (Field f:fields
             ) {
            if (f.isAnnotationPresent(Key.class)){
                Key key=f.getAnnotation(Key.class);
                iskeyAuto=key.value();
            }
            f.setAccessible(true);
            String name=f.getName();
            Object v=f.get(o);
            if(f.getType().getSimpleName().equals("String")){
                String sv="\'"+v+"\'";
                map.put(name,sv);
            }else {
            map.put(name,v);
        }
        }
        String sql=SqlBuilderUtil.createInsertOne(c,map,iskeyAuto);
        //System.out.println(sql);
        MySQLHandler.excute(sql);
    }
    public static void update(Object o) throws IllegalAccessException{
        Class c=o.getClass();
        String tablename=c.getSimpleName();
        Map<String,Object> map=new HashMap<String, Object>();
        Field []fields=c.getDeclaredFields();
        boolean iskeyAuto=false;
        String keyName=null;
        for (Field f:fields
                ) {
            if (f.isAnnotationPresent(Key.class)) {
                Key key = f.getAnnotation(Key.class);
                iskeyAuto = key.value();
                keyName=f.getName();
            }
            f.setAccessible(true);
            String name = f.getName();
            Object v = f.get(o);
            if (f.getType().getSimpleName().equals("String")) {
                String sv = "\'" + v + "\'";
                map.put(name, sv);
            } else {
                map.put(name, v);
            }
        }
        String sql=SqlBuilderUtil.createUpdateOne(c,map,keyName);
        //System.out.println(sql);
        MySQLHandler.excute(sql);

    }
    public static List select(Class clazz) {
//       HibernateTemplate hibernateTemplate;
  //      hibernateTemplate.find("from lojinPO u where u.name=?","name");
        /*
        * 结果集：里面由信息通过getString（）；得到一条记录的一个属性的值
        * Clumnnum是有几个属性
        * 现在要做的就是把这些属性值弄到一个对象中去
        * */
        Object o=null;
        List<Object> list=new LinkedList<Object>();
        try{
            String tname=clazz.getSimpleName();
        String sql=SqlBuilderUtil.createSelectSql(tname);
        ResultSet rs=MySQLHandler.excuteQuery(sql);
        ResultSetMetaData rsmd=rs.getMetaData();
        int num=rsmd.getColumnCount();
        while (rs.next()){
            o=clazz.newInstance();
            for (int i=0;i<num;i++){
                String name=rsmd.getColumnName(i+1);
                Field f=clazz.getDeclaredField(name);
                f.setAccessible(true);
                f.set(o,rs.getObject(name));

            }
            list.add(o);
        }
    }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
