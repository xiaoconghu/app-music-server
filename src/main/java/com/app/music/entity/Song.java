package com.app.music.entity;

import org.springframework.web.multipart.MultipartFile;

public class Song {
    /**
     * id
     */
    private String id;

    /**
     * 歌曲名称
     */
    private String songName;

    /**
     * url
     */
    private String songUrl;

    /**
     * 歌手
     */
    private String singer;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 歌曲封面
     */
    private String songPic;

    /**
     * 歌曲类型
     */
    private String songType;

    /**
     * 所属 cd
     */
    private int cdId;

    /**
     * file
     */
    private MultipartFile file;

    /**
     * 描述
     */

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSongPic() {
        return songPic;
    }

    public void setSongPic(String songPic) {
        this.songPic = songPic;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }


    public int getCdId() {
        return cdId;
    }

    public void setCdId(int cdId) {
        this.cdId = cdId;
    }

    public String getSongType() {

        return songType;
    }

    public void setSongType(String songType) {
        this.songType = songType;
    }
}
