package com.app.music.controller;

import com.app.music.service.IUserService;
import com.app.music.utils.CommonUtils;
import com.app.music.utils.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/music/user")
public class LoginController {
    @Autowired
    IUserService userService;

    /**
     * 登录
     *
     * @param params
     * @param request
     * @return String
     * @throws JsonProcessingException
     */
    @PostMapping("/login")
    public String login(@RequestBody String params, HttpServletRequest request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(params, Map.class);
        Result result = userService.login(map, request);
        return CommonUtils.classToJson(result);
    }

    /**
     * 注册
     *
     * @param params
     * @param request
     * @return String
     */
    @PostMapping("/register")
    public String register(@RequestBody String params, HttpServletRequest request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(params, Map.class);
        Result result = userService.register(map, request);
        return CommonUtils.classToJson(result);
    }

    @PostMapping("/logout")
    public String logout(@RequestBody String params, HttpServletRequest request) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(params, Map.class);
        Result result = userService.logout(map, request);
        return CommonUtils.classToJson(result);

    }


}
