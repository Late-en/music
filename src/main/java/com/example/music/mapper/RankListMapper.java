package com.example.music.mapper;

import com.example.music.entity.RankList;
import org.springframework.stereotype.Repository;

@Repository
public interface RankListMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RankList record);

    int insertSelective(RankList record);

    RankList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RankList record);

    int updateByPrimaryKey(RankList record);
}