package com.app.music.dao;

import com.app.music.entity.Cd;
import com.app.music.entity.Song;

import java.util.List;

public interface ICdDao extends ICommonDao<Cd>{

    List<Song> querySongByCdId();
}
