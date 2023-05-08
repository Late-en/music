package com.example.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.music.entity.Collect;
import com.example.music.mapper.CollectMapper;
import com.example.music.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Late-en
 */

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public boolean addCollection(Collect collect) {
        return collectMapper.insert(collect) == 1;
    }

    @Override
    public boolean existCollection(Long userId, Long songOrListId, Integer type) {
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        if(type == 0) wrapper.eq("song_id", songOrListId);
        else wrapper.eq("song_list_id",songOrListId);
        return collectMapper.selectCount(wrapper) > 0;
    }

    @Override
    public boolean deleteCollection(Long userId, Long songOrListId, Integer type) {
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        if(type == 0) wrapper.eq("song_id", songOrListId);
        else wrapper.eq("song_list_id",songOrListId);
        return collectMapper.delete(wrapper) > 0;
    }

    @Override
    public List<Collect> collectionOfUser(Long userId) {
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return collectMapper.selectList(wrapper);
    }
}
