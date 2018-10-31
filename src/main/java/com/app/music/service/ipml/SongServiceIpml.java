package com.app.music.service.ipml;

import com.app.music.dao.ISongDao;
import com.app.music.entity.Song;
import com.app.music.service.ISongService;
import com.app.music.utils.CommonUtils;
import com.app.music.utils.Result;
import com.app.music.utils.ResultCode;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Service
public class SongServiceIpml implements ISongService {
    @Autowired
    private ISongDao songDao;

    @Override
    public Result insert(Song song) throws IOException {
        String filePath = "/Users/aaa/WebstormProjects";
        String originalFilename = song.getFile().getOriginalFilename();
        String regex = "\\.";
        String[] split = originalFilename.split(regex);
        String fileName = song.getSongName() + "." + split[1];
        try {
            CommonUtils.uploadFile(song.getFile().getBytes(), filePath, fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        song.setSongUrl(filePath + "/" + fileName);
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = sdf.format(new Date());
        song.setCreateTime(new Date());*/
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
        return CommonUtils.success(ResultCode.SUCCESS, query);
    }

    @Override
    public Result queryById(int id) {
        Song song = songDao.queryById(id);
        return CommonUtils.success(ResultCode.SUCCESS, song);
    }

    @Override
    public Result deleteByBatch(String[] arr) {
        songDao.deleteByBatch(arr);
        return CommonUtils.success(ResultCode.SUCCESS, null);
    }

    @Override
    public void getDownload(int songId, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Song song = songDao.queryById(songId);
        String fullPath = song.getSongUrl();
        File downloadFile = new File(fullPath);
        ServletContext context = request.getServletContext();
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
            System.out.println("context getMimeType is null");
        }
        System.out.println("MIME type: " + mimeType);

        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        response.addHeader("Content-Disposition", "attachment;fileName=" + new String(downloadFile.getName()
                .getBytes("UTF-8"), "iso-8859-1"));

        try {
            InputStream myStream = new FileInputStream(fullPath);
            IOUtils.copy(myStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
