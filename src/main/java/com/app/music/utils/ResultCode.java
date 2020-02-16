package com.app.music.utils;

public enum ResultCode {
    /**
     * 请求成功
     */
    SUCCESS(0, "请求成功"),
    LOGIN_ERROR(10001, "用户名或密码错误"),
    NETWORK_ERROR(-1, "网络异常，请稍后重试"),
    LOGIN_USER_ERROR(11000, "用户名已存在"),
    PERMISSIONS_ERROR(11010, "没有操作权限"),
    SONG_ERROR(11001, "歌曲不存在，或已删除");

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
