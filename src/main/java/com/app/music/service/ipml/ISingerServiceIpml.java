package com.app.music.service.ipml;

import com.app.music.dao.ISingerDao;
import com.app.music.entity.Singer;
import com.app.music.service.ISingerService;
import com.app.music.utils.CommonUtils;
import com.app.music.utils.Result;
import com.app.music.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ISingerServiceIpml implements ISingerService {
    @Autowired
    private ISingerDao singerDao;

    @Override
    public Result insert(Singer singer) {
        Boolean aBoolean = singerDao.insert(singer);
        if (aBoolean) {
            return CommonUtils.success(ResultCode.SUCCESS, null);
        } else {
            return CommonUtils.failed(ResultCode.NETWORK_ERROR);
        }
    }

    @Override
    public Result delete(int id) {
        return null;
    }

    @Override
    public Result update(Singer singer) {
        return null;
    }

    @Override
    public Result query() {
        return null;
    }

    @Override
    public Result queryById(int id) {
        return null;
    }
}
