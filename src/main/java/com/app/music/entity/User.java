package com.app.music.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data()
@TableName("user")
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId
    private int id;

    /**
     * 用户登录代码
     */
    @TableField("user_code")
    private String userCode;
    /**
     * 用户昵称
     */
    @TableField("user_name")
    private String userName;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户手机号码
     */
    @TableField("phone_number")
    private String phoneNumber;
    /**
     * 用户密码
     */
    private String password;

}
