package com.app.music.controller;

import com.app.music.entity.Cd;
import com.app.music.service.ICdService;
import com.app.music.utils.CommonUtils;
import com.app.music.utils.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/music/cd")
public class CdController {
    @Autowired
    ICdService cdService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 新增歌单
     *
     * @param params
     * @return
     * @throws IOException
     */
    @PostMapping("/insert")
    public String insert(@RequestBody String params) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Cd cd = mapper.readValue(params, Cd.class);
        Result result = cdService.insert(cd);
        return CommonUtils.classToJson(result);
    }

    /**
     * 删除歌单
     *
     * @param cdId
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/delete/{cdId}")
    public String delete(@PathVariable int cdId) throws JsonProcessingException {
        Result delete = cdService.delete(cdId);
        logger.info(delete.getMsg());
        return CommonUtils.classToJson(delete);
    }

    /**
     * 修改歌单
     * @param params
     * @return
     * @throws IOException
     */
    @PutMapping("/update")
    public Result update(@RequestBody String params) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Cd cd = mapper.readValue(params, Cd.class);
        return cdService.update(cd);

    }

    /**
     * 查询歌单
     * @return
     */
    @GetMapping("/query")
    public Result query(){

        return cdService.query();
    }

    /**
     * 通过id查询歌单
     * @param cdId
     * @return
     */
    @GetMapping("/queryById/{cdId}")
    public Result queryById(@PathVariable int cdId){
        Result result = cdService.queryById(cdId);
        return result;
    }
}
