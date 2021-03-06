package com.app.music.service.ipml;

import com.app.music.dao.ICdDao;
import com.app.music.dao.ISongDao;
import com.app.music.entity.Cd;
import com.app.music.service.ICdService;
import com.app.music.utils.CommonUtils;
import com.app.music.utils.Result;
import com.app.music.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class CdServiceIpml implements ICdService {

    @Autowired
    private ICdDao cdDao;

    @Autowired
    private ISongDao songDao;

    @Override
    public Result insert(Cd cd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = sdf.format(new Date());
        cd.setCreateTime(createTime);
        Boolean insert = cdDao.insert(cd);
        if (insert) {
            return CommonUtils.success(ResultCode.SUCCESS, null);
        } else {
            return CommonUtils.failed(ResultCode.NETWORK_ERROR);
        }
    }

    @Override
    public Result delete(int id) {
        Cd cd = cdDao.queryById(id);
        if (cd != null) {
            songDao.deleteSongsByCd(id);
            cdDao.delete(id);
            return CommonUtils.success(ResultCode.SUCCESS, null);

        } else {
            return CommonUtils.failed(ResultCode.NETWORK_ERROR);
        }

    }

    @Override
    public Result update(Cd cd) {
        Boolean update = cdDao.update(cd);
        if(update){
            return CommonUtils.success(ResultCode.SUCCESS,null);
        }else {
            return CommonUtils.failed(ResultCode.NETWORK_ERROR);
        }
    }

    @Override
    public Result query() {
        List query = cdDao.query();
        return CommonUtils.success(ResultCode.SUCCESS,query);
    }

    @Override
    public Result queryById(int id) {
        return CommonUtils.success(ResultCode.SUCCESS,cdDao.queryById(id));
    }

    @Override
    public Result deleteByBatch(String[] arr) {
        cdDao.deleteByBatch(arr);
        return CommonUtils.success(ResultCode.SUCCESS, null);
    }
}
