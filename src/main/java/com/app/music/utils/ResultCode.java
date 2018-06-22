package com.app.music.utils;

public enum ResultCode {
    /**
     * 请求成功
     */
    SUCCESS(0, "请求成功"),
    LOGIN_ERROR(10001,"用户名或密码错误");

    /**
     * 请求代码
     */
    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
