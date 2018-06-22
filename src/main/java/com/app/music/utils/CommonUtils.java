package com.app.music.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    public static Result success(ResultCode resultCode, List data){
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
}
