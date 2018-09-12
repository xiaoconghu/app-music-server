package com.app.music.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class CommonUtils {

    /**
     * 把类转为JSON
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
     * @param resultCode
     * @param data
     * @return
     */
    public static Result success(ResultCode resultCode, Object data){
        return new Result(resultCode,data);

    }

    /**
     * 失败
     * @param resultCode
     * @return
     */
    public static Result failed(ResultCode resultCode){
        return new Result(resultCode);
    }

    /**
     * 文件上传
     * @param file
     * @param filePath
     * @param fileName
     * @throws Exception
     */
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+"/"+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

}
