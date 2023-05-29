package com.app.music.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data()
@TableName("user")
public class Cd implements Serializable {
    /**
     * id
     */
    @TableId("cd_id")
    private int cdId;
    /**
     * 歌手id
     */
    @TableId("singer_id")
    private int singerId;
    /**
     * 歌手名称
     */
    @TableId("singer_name")
    private String singerName;
    /**
     * 歌单名称
     */
    @TableId("cd_name")
    private String cdName;

    /**
     * 创建人
     */
    @TableId("create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @TableId("create_time")
    private String createTime;

    /**
     * 歌单图片
     */
    @TableId("cd_pic")
    private String cdPic;

    /**
     * 描述
     */
    @TableId("description")
    private String description;

}
