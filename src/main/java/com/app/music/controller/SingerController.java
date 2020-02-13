package com.app.music.controller;

import com.app.music.entity.Singer;
import com.app.music.service.ISingerService;
import com.app.music.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/delete/{singerId}")
    public Result delete(@PathVariable int singerId) {
        return singerService.delete(singerId);
    }

    @PostMapping("/deleteByBatch")
    public Result deleteByBatch(@RequestBody String[] arr) {
        return singerService.deleteByBatch(arr);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Singer singer) {
        return singerService.update(singer);
    }

    /**
     * 查询歌手
     *
     * @return Result
     */
    @GetMapping("/query")
    public Result query() {
        return singerService.query();
    }

    @GetMapping("/queryById/{singerId}")
    public Result queryById(@PathVariable int singerId) {
        return singerService.queryById(singerId);
    }
}
