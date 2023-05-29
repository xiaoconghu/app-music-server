package com.app.music.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface ICommonDao<T> extends BaseMapper<T> {
    /**
     * 删除
     *
     * @param id
     * @return
     */
    Boolean delete(int id);

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
    List<T> queryAll();

    /**
     * 查询单条
     *
     * @param id
     * @return
     */
    T queryById(int id);
}
