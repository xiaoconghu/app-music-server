package com.app.music.service;

import com.app.music.entity.Singer;
import com.app.music.utils.Result;

public interface ISingerService extends ICommonService<Singer> {
    Result deleteByBatch(String[] arr);
}
