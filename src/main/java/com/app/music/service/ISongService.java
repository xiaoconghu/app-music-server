package com.app.music.service;

import com.app.music.entity.Song;
import com.app.music.utils.Result;

public interface ISongService extends ICommonService<Song> {

    Result deleteByBatch(String[] arr);
}
