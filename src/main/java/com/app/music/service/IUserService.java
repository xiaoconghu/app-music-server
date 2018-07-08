package com.app.music.service;

import com.app.music.entity.User;
import com.app.music.utils.Result;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IUserService {

    /**
     * 登录
     *
     * @param map
     * @param request
     * @return Result
     */
    Result login(Map map, HttpServletRequest request) throws JsonProcessingException;

    /**
     * 用户注销登录
     *
     * @param request HttpServletRequest
     * @return Result
     */
    Result logout(Map map, HttpServletRequest request);

    /**
     * 用户注册
     *
     * @param request HttpServletRequest
     * @return Result
     */
    Result register(User user, HttpServletRequest request);
}
