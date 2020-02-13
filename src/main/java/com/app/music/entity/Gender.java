package com.app.music.entity;


public enum Gender {
    man(1, "男"),
    woman(2, "女");

    Gender(int code, String label) {
        this.code = code;
        this.label = label;
    }

    private int code;
    private String label;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}