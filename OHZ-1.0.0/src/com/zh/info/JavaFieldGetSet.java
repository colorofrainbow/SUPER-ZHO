package com.zh.info;

public class JavaFieldGetSet {
    /*
    元素名
     */
    private String fieldInfo;
    /*
    getter
     */
    private String getInfo;
    /*
    setter
     */
    private String SetInfo;

    public String getFieldInfo() {
        return fieldInfo;
    }

    public void setFieldInfo(String fieldInfo) {
        this.fieldInfo = fieldInfo;
    }

    public String getGetInfo() {
        return getInfo;
    }

    public void setGetInfo(String getInfo) {
        this.getInfo = getInfo;
    }

    public String getSetInfo() {
        return SetInfo;
    }

    public void setSetInfo(String setInfo) {
        SetInfo = setInfo;
    }

    public JavaFieldGetSet(){

    }
    public JavaFieldGetSet(String getInfo, String setInfo, String fieldInfo){
        this.fieldInfo=fieldInfo;
        this.getInfo=getInfo;
        this.SetInfo=setInfo;
    }
}
