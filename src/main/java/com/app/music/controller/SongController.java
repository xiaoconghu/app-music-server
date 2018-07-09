package com.app.music.controller;

import com.app.music.utils.Result;
import javafx.application.Application;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/music/song")
public class SongController {
    /**
     * 上传歌曲
     *
     * @return
     */
    @PostMapping("insert")
    public Result insert(MultipartFile file, String fileName) {
        System.err.println(fileName);
        return null;
    }


}
