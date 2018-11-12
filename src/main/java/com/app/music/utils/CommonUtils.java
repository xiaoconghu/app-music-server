package com.app.music.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

public class CommonUtils {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 把类转为JSON
     *
     * @param c
     * @return
     * @throws JsonProcessingException
     */
    public static String classToJson(Object c) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String r = mapper.writeValueAsString(c);
        return r;
    }

    /**
     * 成功
     *
     * @param resultCode
     * @param data
     * @return
     */
    public static Result success(ResultCode resultCode, Object data) {
        return new Result(resultCode, data);

    }

    /**
     * 失败
     *
     * @param resultCode
     * @return
     */
    public static Result failed(ResultCode resultCode) {
        return new Result(resultCode);
    }

    /**
     * 文件上传
     *
     * @param file
     * @param filePath
     * @param fileName
     * @throws Exception
     */
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + "/" + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static String deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                return "删除成功";
            } else {
                return "删除失败";
            }
        } else {
            return "文件不存在";
        }
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        /**
         * X-Forwarded-For:简称XFF头，它代表客户端，也就是HTTP的请求端真实的IP
         * 只有在通过了HTTP 代理或者负载均衡服务器时才会添加该项
         * 标准格式如下：X-Forwarded-For: client_ip, proxy1_ip, proxy2_ip
         * 此头是可构造的，因此某些应用中应该对获取到的ip进行验证
         */
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        /**
         *在多级代理网络中，直接用getHeader("x-forwarded-for")可能获取到的是unknown信息
         *此时需要获取代理代理服务器重新包装的HTTP头信息，
         */
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            //Apache+WebLogic搭配下出现的头
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            //Apache+WebLogic搭配下出现的头
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            //可通过http头伪造，是由代理服务器发送的请求头
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            //可通过http头伪造，和X-Forwarded-For格式类似，以“,"分隔
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        return ip;
    }

}
