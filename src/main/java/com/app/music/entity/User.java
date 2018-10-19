package com.app.music.entity;

import java.io.Serializable;

public class User implements Serializable {
    /**
     * 用户id
     */
    private int id;

    /**
     * 用户登录代码
     */
    private String userCode;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户手机号码
     */
    private String phoneNumber;
    /**
     * 用户密码
     */
    private String password;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
