package com.app.music.dao;

import com.app.music.entity.Song;

public interface ISongDao extends ICommonDao<Song> {

    void deleteSongsByCd(int id);
}
