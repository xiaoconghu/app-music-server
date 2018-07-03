package com.app.music.service.ipml;

import com.app.music.dao.UserDao;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UserServiceIpml implements IUserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Result login(Map map, HttpServletRequest request) throws JsonProcessingException {
        String userCode = (String) map.get("userCode");
        String password = (String) map.get("password");
        User user = userDao.queryUserByUserCode(userCode);
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
            List<Map> list = new ArrayList<>();
            list.add(stringStringMap);

            return CommonUtils.success(ResultCode.SUCCESS, user);
        }

    }

    @Override
    public Result logout(HttpServletRequest request) {
        return null;
    }

    @Override
    public Result register(HttpServletRequest request) {
        return null;
    }
}
