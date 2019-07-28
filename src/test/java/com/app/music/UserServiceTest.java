package com.app.music;

import com.app.music.entity.User;
import com.app.music.service.ipml.UserServiceIpml;
import com.app.music.utils.Md5;
import com.app.music.utils.Result;
import com.app.music.utils.ResultCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

@RunWith(JMockit.class)
public class UserServiceTest {
    @Injectable
    private com.app.music.dao.IUserDao IUserDao;
    @Injectable
    private RedisTemplate redisTemplate;
    @Tested
    private UserServiceIpml userServiceIpml;
    @Mocked
    private HttpServletRequest httpServletRequest;
    @Mocked
    private Md5 md5;

    @Test
    public void contextLoads() throws JsonProcessingException {
        User user = new User();
        user.setUserCode("admin");
        user.setPassword("123456");
        ArrayList<User> list = new ArrayList<>();
        list.add(user);
        Result result = new Result(ResultCode.SUCCESS, list);
        new Expectations(){
            {
                IUserDao.queryUserByUserCode(anyString);
                result = user;
            }
            {
                Md5.encode(anyString);
                result = "123456";
            }
        };
        HashMap<String, String> map = new HashMap<>();
        Result login = userServiceIpml.login(map, httpServletRequest);
        Assert.assertTrue(login.getCode() == ResultCode.SUCCESS.getCode());
        new Expectations(){
            {
                IUserDao.queryUserByUserCode(anyString);
                result = user;
            }
            {
                Md5.encode(anyString);
                result = "12312331231";
            }
        };
        Result loginFail = userServiceIpml.login(map, httpServletRequest);
        Assert.assertTrue(loginFail.getCode() == ResultCode.LOGIN_ERROR.getCode());
        new Expectations(){
            {
                IUserDao.queryUserByUserCode(anyString);
                result = null;
            }
        };
        Result _loginFail = userServiceIpml.login(map, httpServletRequest);
        Assert.assertTrue(_loginFail.getCode() == ResultCode.LOGIN_ERROR.getCode());

    }

}
