package com.app.music.service;

import com.app.music.utils.Result;

public interface ICommonService<T> {
    /**
     * 新增
     *
     * @param t
     * @return
     */
    Result insert(T t);

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
     * @param t
     * @return
     */
    Result update(T t);

    /**
     * 查询
     *
     * @return
     */
    Result query();
}
