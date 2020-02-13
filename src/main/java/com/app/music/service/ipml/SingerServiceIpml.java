package com.app.music.service.ipml;

import com.app.music.dao.ISingerDao;
import com.app.music.entity.Singer;
import com.app.music.service.ISingerService;
import com.app.music.utils.CommonUtils;
import com.app.music.utils.Result;
import com.app.music.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceIpml implements ISingerService {
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
        Boolean aBoolean = singerDao.delete(id);
        if (aBoolean) {
            return CommonUtils.success(ResultCode.SUCCESS, null);
        } else {
            return CommonUtils.failed(ResultCode.NETWORK_ERROR);
        }
    }

    @Override
    public Result update(Singer singer) {
        Singer _singer = singerDao.queryById(singer.getId());
        if (_singer != null) {
            Boolean update = singerDao.update(singer);
            return update ? CommonUtils.success(ResultCode.SUCCESS, null)
                    : CommonUtils.failed(ResultCode.NETWORK_ERROR);
        } else return CommonUtils.failed(ResultCode.SONG_ERROR);
    }

    @Override
    public Result query() {
        List<Singer> query = singerDao.query();
        return CommonUtils.success(ResultCode.SUCCESS, query);
    }

    @Override
    public Result queryById(int id) {
        Singer singer = singerDao.queryById(id);
        return CommonUtils.success(ResultCode.SUCCESS, singer);
    }
    @Override
    public Result deleteByBatch(String[] arr) {
        singerDao.deleteByBatch(arr);
        return CommonUtils.success(ResultCode.SUCCESS, null);
    }
}
