package com.example.music.mapper;

import com.example.music.entity.Singer;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Singer record);

    int insertSelective(Singer record);

    Singer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Singer record);

    int updateByPrimaryKey(Singer record);
}