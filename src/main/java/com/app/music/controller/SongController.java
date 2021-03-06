package com.app.music.controller;

import com.app.music.entity.Song;
import com.app.music.service.ISongService;
import com.app.music.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/music/song")
public class SongController {
    @Autowired
    ISongService songService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 上传歌曲
     *
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Result insert(MultipartFile file, String song) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Song _song = mapper.readValue(song, Song.class);
        _song.setFile(file);
        return songService.insert(_song);
    }

    /**
     * 通过songId 删除歌曲
     *
     * @param songId
     * @return
     */
    @DeleteMapping("/delete/{songId}")
    public Result delete(@PathVariable int songId) {
        return songService.delete(songId);
    }

    @PostMapping("/deleteByBatch")
    public Result deleteByBatch(@RequestBody String[] arr) {
        return songService.deleteByBatch(arr);
    }

    /**
     * 修改歌曲
     *
     * @param params
     * @return
     * @throws IOException
     */
    @PutMapping("/update")
    public Result update(@RequestBody String params) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Song song = mapper.readValue(params, Song.class);
        return songService.update(song);
    }

    /**
     * 查询歌曲
     *
     * @return
     */
    @GetMapping("/query")
    public Result query() {
        return songService.query();
    }

    /**
     * 播放歌曲
     *
     * @param songId   歌曲id
     * @param request  request
     * @param response response
     */
    @RequestMapping(value = "/media/{songId}", method = RequestMethod.GET)
    public void getDownload(@PathVariable int songId, HttpServletRequest request, HttpServletResponse response) {
        try {
            songService.getDownloadLocal(songId,request,response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Get your file stream from wherever.

    }

    @GetMapping("/queryById/{songId}")
    public Result queryById(@PathVariable int songId) {
        return songService.queryById(songId);
    }
}

