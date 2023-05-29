package com.app.music.service;

import com.app.music.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;

public interface ICommonService<T> extends IService<T> {
    /**
     * 新增
     *
     * @param t
     * @return
     */
    Result insert(T t) throws IOException;

    /**
     * 删除
     *
     * @param id
     * @return
     */
    Result delete(int id);

    /**
     * 修改
     *
     * @return
     */
    Result update(T t);

    /**
     * 查询
     *
     * @return
     */
    Result queryAll();

    /**
     * 查询单条
     *
     * @param id
     * @return
     */
    Result queryById(int id);
}
