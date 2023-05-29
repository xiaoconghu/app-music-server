package com.app.music.service.ipml;

import com.app.music.config.CookieUtils;
import com.app.music.dao.IUserDao;
import com.app.music.entity.User;
import com.app.music.service.IUserService;
import com.app.music.utils.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceIpml extends ServiceImpl<IUserDao, User> implements IUserService {

    @Autowired
    private LoadingCache loadingCache;

    public Result login(Map map, HttpServletRequest request) throws JsonProcessingException {
        String ipAddr = CommonUtils.getIpAddr(request);
        System.out.println(ipAddr);
        String userCode = (String) map.get("userCode");
        String password = (String) map.get("password");
        User user = queryUserByUserCode(userCode);
        if (user == null) {
            return CommonUtils.failed(ResultCode.LOGIN_ERROR);
        }
        String encode = Md5.encode(password);
        if (!encode.equals(user.getPassword())) {
            return CommonUtils.failed(ResultCode.LOGIN_ERROR);
        } else {
            Map<String, String> stringStringMap = new HashMap<>();
            String s = CommonUtils.classToJson(user);
            stringStringMap.put("user", s);
            ArrayList<User> list = new ArrayList<>();
            list.add(user);
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            CookieUtils.setCookie("token", token);
            loadingCache.put(token, user);
            return CommonUtils.success(ResultCode.SUCCESS, list);
        }

    }

    @Cacheable(cacheNames = "emp")
    public String getTokenObject(String token, String userId) {
        System.out.println("查找key为：" + userId);
        return userId;
    }

    @Override
    public Result logout(Map map, HttpServletRequest request) {

        if ("admin".equals(map.get("userCode"))) {
            System.out.println(map);
        } else {
            System.out.println("当前退出的用户为" + map.get("userCode"));
        }
        return CommonUtils.success(ResultCode.SUCCESS, null);
    }

    @Override
    public Result register(User user, HttpServletRequest request) {
        User _user = queryUserByUserCode(user.getUserCode());
        if (_user != null) {
            return CommonUtils.failed(ResultCode.LOGIN_USER_ERROR);
        } else {
            String encode = Md5.encode(user.getPassword());
            user.setPassword(encode);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createTime = sdf.format(new Date());
            user.setCreateTime(createTime);

            User addUser = new User();
            BeanUtils.copyProperties(user, addUser);
            Boolean insertUser = this.save(addUser);
            if (insertUser) {
                return CommonUtils.success(ResultCode.SUCCESS, null);

            } else {
                return CommonUtils.failed(ResultCode.NETWORK_ERROR);
            }
        }
    }

    private User queryUserByUserCode(String userCode) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery().eq(User::getUserCode, userCode);
        return this.getOne(wrapper);
    }

    @Override
    public Result updateUser(Map map, HttpServletRequest request) {
        int userId = Integer.parseInt(map.get("userId").toString());
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery().eq(User::getId, userId);
        User user = this.getOne(wrapper);
        if (user != null) {
            User addUser = new User();
            BeanUtils.copyProperties(map, addUser);
            boolean aBoolean = this.updateById(addUser);
            if (aBoolean) {
                return CommonUtils.success(ResultCode.SUCCESS, null);
            } else {
                return CommonUtils.failed(ResultCode.NETWORK_ERROR);
            }
        } else {
            return CommonUtils.failed(ResultCode.NETWORK_ERROR);
        }
    }

    @Override
    public Result getAllUser() {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery().ne(User::getUserCode, "admin");
        List<User> list = this.list(wrapper);
        return CommonUtils.success(ResultCode.SUCCESS, list);
    }

    @Override
    public Result deleteUserById(String userId) {
        boolean aBoolean = this.removeById(userId);
        if (aBoolean) {
            return CommonUtils.success(ResultCode.SUCCESS, null);
        } else {
            return CommonUtils.failed(ResultCode.NETWORK_ERROR);
        }
    }

    @Override
    public Result deleteUserByBatch(int[] userIds) {
        boolean aBoolean = this.removeByIds(Arrays.asList(userIds));
        return CommonUtils.success(ResultCode.SUCCESS, aBoolean);

    }

    @Override
    public Result getUserById(int userId) {
        User user = this.getById(userId);
        return CommonUtils.success(ResultCode.SUCCESS, user);
    }
}
