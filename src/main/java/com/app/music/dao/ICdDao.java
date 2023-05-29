package com.app.music.dao;

import com.app.music.entity.Cd;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface ICdDao extends BaseMapper<Cd> {
    List<Cd> queryAll();
}
