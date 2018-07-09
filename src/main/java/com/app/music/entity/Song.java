package com.app.music.entity;

public class Song {
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
    private String type;

    /**
     * 所属 cd
     */
    private String cdId;

    /**
     * 描述
     */

    private String description;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCdId() {
        return cdId;
    }

    public void setCdId(String cdId) {
        this.cdId = cdId;
    }
}
