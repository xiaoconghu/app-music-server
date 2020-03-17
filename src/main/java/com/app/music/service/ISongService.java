package com.app.music.service;

import com.app.music.entity.Song;
import com.app.music.utils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ISongService extends ICommonService<Song> {

    Result deleteByBatch(String[] arr);


    void getDownloadLocal(int songId, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
