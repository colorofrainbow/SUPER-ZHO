package com.zh.info;

public class ConfigurationInfo {
    /*保存以下信息
    * driver
    * 数据库url
    * username
    * password
    * 数据库类型
    * src的地址
    * 保存po对象的地址
    * */
    private String driver;
    private String url;
    private String username;
    private String password;
    private String dbType;
    private String srcAddress;
    private String poPackageName;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getSrcAddress() {
        return srcAddress;
    }

    public void setSrcAddress(String srcAddress) {
        this.srcAddress = srcAddress;
    }

    public String getPoPackageName() {
        return poPackageName;
    }

    public void setPoPackageName(String poPackageName) {
        this.poPackageName = poPackageName;
    }
}
