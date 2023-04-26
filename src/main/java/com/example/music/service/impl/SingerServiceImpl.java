package com.example.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.music.entity.Singer;
import com.example.music.mapper.SingerMapper;
import com.example.music.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author Late-en
 */
@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    private SingerMapper singerMapper;

    @Override
    public boolean addSinger(Singer singer) {
        return singerMapper.insert(singer) == 1;
    }

    @Override
    public boolean existSinger(String name){
        QueryWrapper<Singer> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return singerMapper.selectCount(wrapper) == 1;
    }

    @Override
    public boolean deleteSinger(Serializable id) {
        return singerMapper.deleteById(id) == 1;
    }

    @Override
    public boolean updateSingerInfo(Singer singer) {
        return singerMapper.updateById(singer) == 1;
    }

    @Override
    public boolean updateSingerPic(Singer singer) {
        return singerMapper.updateById(singer) == 1;
    }

    @Override
    public List<Singer> allSinger() {
        return singerMapper.selectList(null);
    }

    @Override
    public List<Singer> SingerOfName(String name) {
        QueryWrapper<Singer> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return singerMapper.selectList(wrapper);
    }

    @Override
    public List<Singer> SingerOfSex(Integer sex) {
        QueryWrapper<Singer> wrapper = new QueryWrapper<>();
        wrapper.eq("sex", sex);
        return singerMapper.selectList(wrapper);
    }
}
