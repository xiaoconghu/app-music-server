package com.app.music.service;

import com.app.music.utils.Result;

import java.io.IOException;

public interface ICommonService<T> {
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
    Result delete(String id);

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
    Result query();

    /**
     * 查询单条
     *
     * @param id
     * @return
     */
    T queryById(String id);
}
