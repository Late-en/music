package com.example.music.mapper;

import com.example.music.entity.ListSong;
import org.springframework.stereotype.Repository;

@Repository
public interface ListSongMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ListSong record);

    int insertSelective(ListSong record);

    ListSong selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ListSong record);

    int updateByPrimaryKey(ListSong record);
}