package com.app.music.service.ipml;

import com.app.music.dao.ISingerDao;
import com.app.music.entity.Singer;
import com.app.music.service.ISingerService;
import com.app.music.utils.CommonUtils;
import com.app.music.utils.Result;
import com.app.music.utils.ResultCode;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SingerServiceIpml extends ServiceImpl<ISingerDao, Singer>  implements ISingerService {

    @Override
    public Result insert(Singer singer) {
        Boolean aBoolean = this.save(singer);
        if (aBoolean) {
            return CommonUtils.success(ResultCode.SUCCESS, null);
        } else {
            return CommonUtils.failed(ResultCode.NETWORK_ERROR);
        }
    }

    @Override
    public Result delete(int id) {
        Boolean aBoolean = this.removeById(id);
        if (aBoolean) {
            return CommonUtils.success(ResultCode.SUCCESS, null);
        } else {
            return CommonUtils.failed(ResultCode.NETWORK_ERROR);
        }
    }

    @Override
    public Result update(Singer singer) {
        Singer _singer = this.getById(singer.getId());
        if (_singer != null) {
            Boolean update = this.saveOrUpdate(singer);
            return update ? CommonUtils.success(ResultCode.SUCCESS, null)
                    : CommonUtils.failed(ResultCode.NETWORK_ERROR);
        } else return CommonUtils.failed(ResultCode.SONG_ERROR);
    }

    @Override
    public Result queryAll() {
        List<Singer> query = this.baseMapper.queryAll();
        return CommonUtils.success(ResultCode.SUCCESS, query);
    }

    @Override
    public Result queryById(int id) {
        Singer singer = this.getById(id);
        return CommonUtils.success(ResultCode.SUCCESS, singer);
    }
    @Override
    public Result deleteByBatch(String[] arr) {
        this.removeByIds(Arrays.asList(arr));
        return CommonUtils.success(ResultCode.SUCCESS, null);
    }
}
