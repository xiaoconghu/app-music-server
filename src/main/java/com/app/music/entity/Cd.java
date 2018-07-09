package com.app.music.entity;

public class Cd {

    /**
     * 歌单名称
     */
    private String cdName;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 歌单图片
     */
    private String cdPic;

    /**
     * 描述
     */
    private String description;

    public String getCdName() {
        return cdName;
    }

    public void setCdName(String cdName) {
        this.cdName = cdName;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCdPic() {
        return cdPic;
    }

    public void setCdPic(String cdPic) {
        this.cdPic = cdPic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
