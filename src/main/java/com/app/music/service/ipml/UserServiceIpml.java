package com.app.music.service.ipml;

import com.app.music.dao.IUserDao;
import com.app.music.entity.User;
import com.app.music.service.IUserService;
import com.app.music.utils.CommonUtils;
import com.app.music.utils.Md5;
import com.app.music.utils.Result;
import com.app.music.utils.ResultCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceIpml implements IUserService {
    @Autowired
    private IUserDao IUserDao;

    @Override
    public Result login(Map map, HttpServletRequest request) throws JsonProcessingException {
        String ipAddr = CommonUtils.getIpAddr(request);
        System.out.println(ipAddr);
        String userCode = (String) map.get("userCode");
        String password = (String) map.get("password");
        User user = IUserDao.queryUserByUserCode(userCode);
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

            return CommonUtils.success(ResultCode.SUCCESS, list);
        }

    }

    @Override
    public Result logout(Map map, HttpServletRequest request) {
        return null;
    }

    @Override
    public Result register(User user, HttpServletRequest request) {
        User _user = IUserDao.queryUserByUserCode(user.getUserCode());
        if (_user != null) {
            return CommonUtils.failed(ResultCode.LOGIN_USER_ERROR);
        } else {
            String encode = Md5.encode(user.getPassword());
            user.setPassword(encode);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createTime = sdf.format(new Date());
            user.setCreateTime(createTime);
            Boolean insertUser = IUserDao.insertUser(user);
            if (insertUser) {
                return CommonUtils.success(ResultCode.SUCCESS, null);

            } else {
                return CommonUtils.failed(ResultCode.NETWORK_ERROR);
            }
        }
    }

    @Override
    public Result updateUser(Map map, HttpServletRequest request) {
        int userId = (int) map.get("userId");
        User user = IUserDao.queryUserByUserId(userId);
        if (user != null) {
            Boolean aBoolean = IUserDao.updateUser(map);
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
        return CommonUtils.success(ResultCode.SUCCESS, IUserDao.queryAllUser());
    }

    @Override
    public Result deleteUserById(String userId) {
        Boolean aBoolean = IUserDao.deleteUserByUserId(userId);
        if (aBoolean) {
            return CommonUtils.success(ResultCode.SUCCESS, null);
        } else {
            return CommonUtils.failed(ResultCode.NETWORK_ERROR);
        }
    }

    @Override
    public Result deleteUserByBatch(int[] userIds) {
        IUserDao.deleteUserByBatch(userIds);
        return CommonUtils.success(ResultCode.SUCCESS, null);

    }

    @Override
    public Result getUserById(int userId) {
        User user = IUserDao.queryUserByUserId(userId);
        return CommonUtils.success(ResultCode.SUCCESS, user);
    }
}
