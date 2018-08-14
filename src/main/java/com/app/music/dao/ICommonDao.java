package com.app.music.dao;

import java.util.List;

public interface ICommonDao<T> {
    /**
     * 新增
     *
     * @param t
     * @return
     */
    Boolean insert(T t);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    Boolean delete(String id);

    /**
     * 修改
     *
     * @param t
     * @return
     */
    Boolean update(T t);

    /**
     * 查询
     *
     * @return
     */
    List<T> query();

    /**
     * 查询单条
     *
     * @param id
     * @return
     */
    T queryById(String id);
}
