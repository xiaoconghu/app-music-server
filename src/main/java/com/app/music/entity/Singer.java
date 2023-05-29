package com.app.music.entity;

import lombok.Data;

import java.io.Serializable;
@Data()
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
}
