package com.app.music.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data()
@TableName("song")
public class Song implements Serializable {
    /**
     * id
     */
    @TableId()
    private int id;

    /**
     * 歌曲名称
     */
    @TableField("song_name")
    private String songName;

    /**
     * url
     */
    @TableField("song_url")
    private String songUrl;

    /**
     * 歌手
     */
    private String singer;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;

    /**
     * 歌曲封面
     */
    @TableField("song_pic")
    private String songPic;

    /**
     * 歌曲类型
     */
    @TableField("song_type")
    private String songType;

    /**
     * 所属 cd
     */
    @TableField("cd_id")
    private int cdId;

    /**
     * file
     */
    @TableField(exist = false)
    private MultipartFile file;

    /**
     * 描述
     */

    private String description;
}
