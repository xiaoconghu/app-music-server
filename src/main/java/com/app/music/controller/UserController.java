package com.app.music.controller;

import com.app.music.entity.User;
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
public class UserController {
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
     * @param user
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/register")
    public String register(@RequestBody String params, HttpServletRequest request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(params, User.class);
        Result result = userService.register(user, request);
        return CommonUtils.classToJson(result);
    }

    /**
     * 登出
     * @param params
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/logout")
    public String logout(@RequestBody String params, HttpServletRequest request) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(params, Map.class);
        Result result = userService.logout(map, request);
        return CommonUtils.classToJson(result);

    }

    /**
     * 修改用户
     * @param params
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/updateUser")
    public String updateUser(@RequestBody String params, HttpServletRequest request) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(params, Map.class);
        Result result = userService.updateUser(map, request);
        return CommonUtils.classToJson(result);

    }

    /**
     * 获取所有的用户
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/getAllUser")
    public String getAllUser() throws JsonProcessingException {
        Result allUser = userService.getAllUser();
        return CommonUtils.classToJson(allUser);
    }

    /**
     * 通过id删除用户
     * @param userId
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/deleteUserById/{userId}")
    public String deleteUserById(@PathVariable String userId) throws JsonProcessingException {
        Result allUser = userService.deleteUserById(userId);
        return CommonUtils.classToJson(allUser);
    }

}
