package com.zh.configure;

import com.zh.info.ColumnInfo;
import com.zh.info.TableInfo;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TableContext {
    public static Map<String,TableInfo> tables=new HashMap<String,TableInfo>();
    static {
        try {
            DatabaseMetaData dm=dbManger.getMetaData();
            ResultSet tableRS = dm.getTables(null,"%","%",new String[]{"TABLE"});
            while(tableRS.next()){
                String tableName=(String) tableRS.getObject("TABLE_NAME");
                TableInfo ti=new TableInfo(tableName,new HashMap<String,ColumnInfo>(),new ArrayList<ColumnInfo>());
                tables.put(tableName,ti);
                ResultSet columnRS=dm.getColumns(null,"%",tableName,"%");
                while (columnRS.next()){
                    ColumnInfo ci=new ColumnInfo(columnRS.getString("COLUMN_NAME"),columnRS.getString("TYPE_NAME"),0);
                    ti.getColumns().put(columnRS.getString("COLUMN_NAME"),ci);
                }
                ResultSet keySet=dm.getPrimaryKeys(null,"%",tableName);
                while(keySet.next()){
                    String columnName=keySet.getString("COLUMN_NAME");
                    ColumnInfo ci1=ti.getColumns().get(columnName);
                    ci1.setKeyType(1);
                    ti.getPriKeys().add(ci1);
                }
                if (ti.getPriKeys().size()>0){//取唯一主键，如果是联合主键，则为空
                    ti.setOnlyPriKey(ti.getPriKeys().get(0));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
