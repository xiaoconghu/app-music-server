package com.app.music.entity;

import java.io.Serializable;

public class Singer implements Serializable {
    /**
     * id
     */
    private int id;
    /**
     * 歌手名称
     */
    private String singerName;
    /**
     * 歌手昵称
     */
    private String singerNickName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 歌手头像
     */
    private String singerPic;
    /**
     * 性别
     */
    private int singerGender;
    /**
     * 描述
     */
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerNickName() {
        return singerNickName;
    }

    public void setSingerNickName(String singerNickName) {
        this.singerNickName = singerNickName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSingerPic() {
        return singerPic;
    }

    public void setSingerPic(String singerPic) {
        this.singerPic = singerPic;
    }

    public int getSingerGender() {
        return singerGender;
    }

    public void setSingerGender(int singerGender) {
        this.singerGender = singerGender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
