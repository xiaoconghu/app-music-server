package com.app.music.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * @Description: 阿里云 OSS 配置信息
 * @Author: junqiang.lu
 * @Date: 2019/5/9
 */
@Configuration
public class OSSConfig implements Serializable {

    private static final long serialVersionUID = -119396871324982279L;

    /**
     * 阿里云 oss 站点
     */
    @Value("${oss.endpoint}")
    private String endpoint;

    /**
     * 阿里云 oss 资源访问 url
     */
    @Value("${oss.url}")
    private String url;

    /**
     * 阿里云 oss 公钥
     */
    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    /**
     * 阿里云 oss 私钥
     */
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    /**
     * 阿里云 oss 文件根目录
     */
    @Value("${oss.bucketName}")
    private String bucketName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
