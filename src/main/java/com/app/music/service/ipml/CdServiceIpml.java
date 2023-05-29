package com.app.music.service.ipml;

import com.app.music.dao.ICdDao;
import com.app.music.dao.ISongDao;
import com.app.music.dao.IUserDao;
import com.app.music.entity.Cd;
import com.app.music.entity.User;
import com.app.music.service.ICdService;
import com.app.music.utils.CommonUtils;
import com.app.music.utils.Result;
import com.app.music.utils.ResultCode;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class CdServiceIpml extends ServiceImpl<ICdDao, Cd> implements ICdService {

    @Autowired
    private ISongDao songDao;


    public Result insert(Cd cd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = sdf.format(new Date());
        cd.setCreateTime(createTime);
        boolean insert = this.save(cd);
        if (insert) {
            return CommonUtils.success(ResultCode.SUCCESS, null);
        } else {
            return CommonUtils.failed(ResultCode.NETWORK_ERROR);
        }
    }


    public Result delete(int id) {
        Cd cd = this.getById(id);
        if (cd != null) {
            songDao.deleteSongsByCd(id);
            this.removeById(id);
            return CommonUtils.success(ResultCode.SUCCESS, null);

        } else {
            return CommonUtils.failed(ResultCode.NETWORK_ERROR);
        }

    }


    public Result update(Cd cd) {
        boolean update = this.saveOrUpdate(cd);
        if (update) {
            return CommonUtils.success(ResultCode.SUCCESS, null);
        } else {
            return CommonUtils.failed(ResultCode.NETWORK_ERROR);
        }
    }

    @Override
    public Result queryAll() {
        List query = this.baseMapper.queryAll();
        return CommonUtils.success(ResultCode.SUCCESS, query);
    }


    public Result queryById(int id) {
        return CommonUtils.success(ResultCode.SUCCESS, this.getById(id));
    }

    @Override
    public Result deleteByBatch(String[] arr) {
        this.removeByIds(Arrays.asList(arr));
        return CommonUtils.success(ResultCode.SUCCESS, null);
    }
}
