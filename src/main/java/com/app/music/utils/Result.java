package com.app.music.utils;

import java.util.ArrayList;

public class Result {
    private int code;
    private String msg;
    private Object data;

    public Result() {

    }

    public Result(ResultCode resultCode, Object data) {
        this(resultCode);
        if (data == null || "".equals(data)) {
            this.data = new ArrayList<Object>();
        } else {
            this.data = data;
        }
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
