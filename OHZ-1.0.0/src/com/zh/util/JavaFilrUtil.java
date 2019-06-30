package com.zh.util;

import com.zh.configure.TableContext;
import com.zh.configure.XmlOrProperties;
import com.zh.info.ColumnInfo;
import com.zh.info.ConfigurationInfo;
import com.zh.info.JavaFieldGetSet;
import com.zh.info.TableInfo;
import com.zh.typeChange.databaseType2JavaTypeImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JavaFilrUtil {
    /*
    create getter and setter
     */
    public static JavaFieldGetSet createFieldGetSet(ColumnInfo column, databaseType2JavaTypeImpl dt){
        JavaFieldGetSet jfgs=new JavaFieldGetSet();
        //得到这个列的fieldname
        String fieldName=column.getName();
        //得到这个列的fieldType
        String fielfType=dt.databaseType2JavaType(column.getDataType());
        //写出属性声明
        StringBuilder sbField=new StringBuilder();
        sbField.append("\tprivate "+fielfType+" "+fieldName+";\n");
        //写出set方法
        StringBuilder sbSet=new StringBuilder();
        sbSet.append("\tpublic void set").append(fieldName.substring(0,1).toUpperCase()).append(fieldName.substring(1));
        sbSet.append(" ("+fielfType+" "+fieldName+") ");
        sbSet.append("{\n\t").append("\tthis."+fieldName+"="+fieldName+";\n"+"\t}\n");
        //得到get方法
        StringBuilder sbGet=new StringBuilder();
        sbGet.append("\tpublic ").append(fielfType).append(" get").append(fieldName.substring(0,1).toUpperCase()).append(fieldName.substring(1));
        sbGet.append("() {\n").append("\t").append("\treturn "+fieldName+";\n"+"\t}\n");
        jfgs.setFieldInfo(sbField.toString());
        jfgs.setGetInfo(sbGet.toString());
        jfgs.setSetInfo(sbSet.toString());
        return jfgs;
    }
    /*
    create class

     */
    public static  String createJavaSrc(TableInfo tableInfo){
        Map<String,ColumnInfo> columns=tableInfo.getColumns();
        List<JavaFieldGetSet> javaFieldGetSetsList=new LinkedList<JavaFieldGetSet>();
        databaseType2JavaTypeImpl dt=new databaseType2JavaTypeImpl();
        for(ColumnInfo columnInfo:columns.values()){
            javaFieldGetSetsList.add(createFieldGetSet(columnInfo,dt));
        }
        StringBuilder sbSrc=new StringBuilder();
        String src=XmlOrProperties.getConf().getPoPackageName();
        sbSrc.append("package "+src+";\n\n");
        sbSrc.append("public class "+tableInfo.getTname()+"{\n");
        for (JavaFieldGetSet jfgs:javaFieldGetSetsList) {
            sbSrc.append(jfgs.getFieldInfo());
        }
        for (JavaFieldGetSet jfgs:javaFieldGetSetsList) {
            sbSrc.append(jfgs.getGetInfo());
        }
        for (JavaFieldGetSet jfgs:javaFieldGetSetsList) {
            sbSrc.append(jfgs.getSetInfo());
        }
        sbSrc.append("\n}");
        return sbSrc.toString();
    }
    /*
    write in the file
     */
    public static void createJavaFiles(TableInfo tableInfo) {
        ConfigurationInfo conf = XmlOrProperties.getConf();
        String pp = conf.getPoPackageName().replace(".", "/");
        String src = createJavaSrc(tableInfo);
        File f = new File(conf.getSrcAddress() + "/" + pp + "/" + tableInfo.getTname() + ".java");
        BufferedWriter bw;
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(f.getAbsoluteFile()));
            bw.write(src);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void updateJavaPOFile(){
        Map<String,TableInfo>tables=TableContext.tables;
//        System.out.println(tables.values());
        for(TableInfo tableInfo:tables.values()){
            createJavaFiles(tableInfo);
        }
    }

}
