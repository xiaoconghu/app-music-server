package com.app.music.service.ipml;

import com.app.music.dao.ISongDao;
import com.app.music.entity.Song;
import com.app.music.service.ISongService;
import com.app.music.utils.CommonUtils;
import com.app.music.utils.Result;
import com.app.music.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SongServiceIpml implements ISongService {
    @Autowired
    private ISongDao songDao;

    @Override
    public Result insert(Song song) throws IOException {
        String filePath = "G:\\music";
        String originalFilename = song.getFile().getOriginalFilename();
        String regex = "\\.";
        String[] split = originalFilename.split(regex);
        String fileName = song.getSongName() + "." + split[1];
        try {
            CommonUtils.uploadFile(song.getFile().getBytes(), filePath, fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        song.setSongUrl(filePath + "\\" + fileName);
        Boolean aBoolean = songDao.insert(song);
        if (aBoolean) {
            return CommonUtils.success(ResultCode.SUCCESS, null);
        } else {
            return CommonUtils.failed(ResultCode.NETWORK_ERROR);
        }

    }

    @Override
    public Result delete(String id) {
        return null;
    }

    @Override
    public Result update(Song song) {
        return null;
    }

    @Override
    public Result query() {
        return null;
    }
}
