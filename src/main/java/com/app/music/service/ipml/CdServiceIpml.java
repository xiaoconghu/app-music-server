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

@Service
@Transactional(rollbackFor = Exception.class)
public class CdServiceIpml implements ICdService {

    @Autowired
    private ICdDao cdDao;

    @Autowired
    private ISongDao songDao;

    @Override
    public Result insert(Cd cd) {
        Boolean insert = cdDao.insert(cd);
        if (insert) {
            return CommonUtils.success(ResultCode.SUCCESS, null);
        } else {
            return CommonUtils.failed(ResultCode.NETWORK_ERROR);
        }
    }

    @Override
    public Result delete(String id) {
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
        return null;
    }

    @Override
    public Cd queryById(String id) {
        return null;
    }
}
