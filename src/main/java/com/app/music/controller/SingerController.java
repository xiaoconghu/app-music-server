package com.app.music.controller;

import com.app.music.entity.Singer;
import com.app.music.service.ISingerService;
import com.app.music.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/music/singer")
public class SingerController {
    @Autowired
    ISingerService singerService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/insert")
    public Result insert(@RequestBody Singer singer) throws IOException {
        return singerService.insert(singer);
    }
}
