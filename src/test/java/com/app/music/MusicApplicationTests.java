package com.app.music;

import com.app.music.entity.User;
import com.app.music.service.IUserService;
import com.app.music.utils.Result;
import com.app.music.utils.ResultCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

@RunWith(JUnit4.class)
@WebAppConfiguration
@SpringBootTest
public class MusicApplicationTests {
    @Autowired
    IUserService userService;
    @Autowired
    private com.app.music.dao.IUserDao IUserDao;
    @Resource
    private RedisTemplate redisTemplate;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test()
    public void contextLoads() throws JsonProcessingException{
        User user = new User();
        user.setUserCode("admin");
        user.setPassword("123456");
        ArrayList<User> list = new ArrayList<>();
        list.add(user);
        Result result = new Result(ResultCode.SUCCESS, list);

        HashMap<String, String> map = new HashMap<>();
        Mockito.when(userService.login(map, request)).thenReturn(result);

    }

}
