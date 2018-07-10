package com.app.music.service.ipml;

import com.app.music.dao.ICdDao;
import com.app.music.entity.Cd;
import com.app.music.service.ICdService;
import com.app.music.utils.CommonUtils;
import com.app.music.utils.Result;
import com.app.music.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CdServiceIpml implements ICdService {
    @Autowired
    ICdDao cdDao;

    @Override
    public Result insert(Cd cd) {
        Boolean insert = cdDao.insert(cd);
        if(insert){
            return CommonUtils.success(ResultCode.SUCCESS,null);
        }else {
            return CommonUtils.failed(ResultCode.NETWORK_ERROR);
        }
    }

    @Override
    public Result delete(String id) {
        return null;
    }

    @Override
    public Result update(Cd cd) {
        return null;
    }

    @Override
    public Result query() {
        return null;
    }
}
