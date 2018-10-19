package com.app.music.controller;

import com.app.music.entity.User;
import com.app.music.service.IUserService;
import com.app.music.utils.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
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
    public Result login(@RequestBody String params, HttpServletRequest request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(params, Map.class);
        return userService.login(map, request);
    }

    /**
     * 注册
     *
     * @param params
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/register")
    public Result register(@RequestBody String params, HttpServletRequest request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(params, User.class);
        return userService.register(user, request);
    }

    /**
     * 登出
     *
     * @param params
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/logout")
    public Result logout(@RequestBody String params, HttpServletRequest request) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(params, Map.class);
        return userService.logout(map, request);

    }

    /**
     * 修改用户
     *
     * @param params
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody String params, HttpServletRequest request) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(params, Map.class);
        return userService.updateUser(map, request);

    }

    /**
     * 获取所有的用户
     *
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/getAllUser")
    public Result getAllUser() throws JsonProcessingException {
        return userService.getAllUser();
    }

    /**
     * 通过id删除用户
     *
     * @param userId
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/deleteUserById/{userId}")
    public Result deleteUserById(@PathVariable String userId) throws JsonProcessingException {
        return userService.deleteUserById(userId);
    }

    @PostMapping("/deleteUserByBatch")
    public Result deleteUserByBatch(@RequestBody int[] userIds) {

        return userService.deleteUserByBatch(userIds);
    }

    @GetMapping("/getUserById/{id}")
    public Result getUserById(@PathVariable("id") int userId) {
        return userService.getUserById(userId);
    }
}
