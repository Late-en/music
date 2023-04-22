package com.example.music.mapper;

import com.example.music.entity.Song;
import org.springframework.stereotype.Repository;

@Repository
public interface SongMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Song record);

    int insertSelective(Song record);

    Song selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Song record);

    int updateByPrimaryKeyWithBLOBs(Song record);

    int updateByPrimaryKey(Song record);
}