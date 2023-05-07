package com.example.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.music.entity.ListSong;
import com.example.music.mapper.ListSongMapper;
import com.example.music.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Late-en
 */

@Service
public class ListSongServiceImpl implements ListSongService {
    @Autowired
    private ListSongMapper listSongMapper;

    @Override
    public boolean addListSong(ListSong listSong) {
        return listSongMapper.insert(listSong) == 1;
    }

    @Override
    public boolean updateListSongInfo(ListSong listSong) {
        return listSongMapper.updateById(listSong) == 1;
    }

    @Override
    public boolean deleteListSong(Long songId, Long songListId) {
        QueryWrapper<ListSong> wrapper = new QueryWrapper<>();
        wrapper.eq("song_id", songId).eq("song_list_id", songListId);
        return listSongMapper.delete(wrapper) == 1;
    }

    @Override
    public List<ListSong> allListSong() {
        return listSongMapper.selectList(null);
    }

    @Override
    public List<Long> SongsOfSongList(Long songListId) {
        QueryWrapper<ListSong> wrapper = new QueryWrapper<>();
        wrapper.eq("song_list_id", songListId);
        List<ListSong> listSongs =  listSongMapper.selectList(wrapper);
        return listSongs.stream().map(ListSong::getSongId).collect(Collectors.toList());
    }
}
