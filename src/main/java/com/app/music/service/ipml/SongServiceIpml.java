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
import java.util.List;

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
    public Result delete(int id) {
        Boolean delete = songDao.delete(id);

        return delete ? CommonUtils.success(ResultCode.SUCCESS, null)
                : CommonUtils.failed(ResultCode.NETWORK_ERROR);
    }

    @Override
    public Result update(Song song) {
        Song _song = songDao.queryById(song.getId());
        if (_song != null) {
            Boolean update = songDao.update(song);
            return update ? CommonUtils.success(ResultCode.SUCCESS, null)
                    : CommonUtils.failed(ResultCode.NETWORK_ERROR);
        } else return CommonUtils.failed(ResultCode.SONG_ERROR);
    }

    @Override
    public Result query() {
        List<Song> query = songDao.query();
        return CommonUtils.success(ResultCode.SUCCESS,query);
    }

    @Override
    public Result queryById(int id) {
        Song song = songDao.queryById(id);
        return CommonUtils.success(ResultCode.SUCCESS,song);
    }
}
