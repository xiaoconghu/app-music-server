package com.app.music.service;

import com.app.music.entity.Cd;
import com.app.music.utils.Result;

public interface ICdService extends ICommonService<Cd> {

    Result deleteByBatch(String[] arr);
}
