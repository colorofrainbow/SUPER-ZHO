package com.zh.typeChange;

public class databaseType2JavaTypeImpl implements databaseType2JavaType {

    @Override
    public String databaseType2JavaType(String Clomn) {
        switch(Clomn){
            case "VARCHAR":
            case "CHAR":return "String";
            case "SMALLINT":
            case "INT":
            case "TINYINT":return "Integer";
            case "BIGINT":return "Long";
            case "DOUBLE":return "Double";
            case "FLOAT":return "Double";
            case "CLOB":return "java.sql.Clob";
            case "BLOB":return "java.sql.Blob";
            case "DATE":return "java.sql.Date";
            case "TIME":return "java.sql.Time";
            case "TIMESTAMP":return "java.sql.Timestamp";
            default:return null;
        }
    }
}
