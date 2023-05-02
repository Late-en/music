package com.example.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.music.constant.Path;
import com.example.music.entity.Song;
import com.example.music.mapper.SongMapper;
import com.example.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Late-en
 */

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongMapper songMapper;

    @Override
    public boolean addSong(MultipartFile songFile, Song song) {
        File folder = new File(Path.DATA_SONG_PATH);
        if(!folder.exists()) folder.mkdir();
        File dest = new File(Path.PROJECT_DATA_PATH + song.getUrl());
        try {
            songFile.transferTo(dest);
            if(songMapper.insert(song) != 1){
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateSongInfo(Song song) {
        return songMapper.updateById(song) == 1;
    }

    @Override
    public boolean updateSongUrl(MultipartFile songFile, Long id) {
        File dest = new File(Path.DATA_SONG_PATH + System.getProperty("file.separator") + songFile.getOriginalFilename());
        Song song = Song.builder().id(id).url("/song/" + songFile.getOriginalFilename()).build();
        try {
            songFile.transferTo(dest);
            if(songMapper.updateById(song) != 1){
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateSongPic(MultipartFile picFile, Long id) {
        String fileName = System.currentTimeMillis() + picFile.getOriginalFilename();
        File dest = new File(Path.IMAGE_SONG_PATH + System.getProperty("file.separator") + fileName);
        Song song = Song.builder().id(id).pic("/img/songPic/" + fileName).build();
        try {
            picFile.transferTo(dest);
            if(songMapper.updateById(song) != 1){
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteSong(Long id) {
        return songMapper.deleteById(id) == 1;
    }

    @Override
    public List<Song> allSong() {
        return songMapper.selectList(null);
    }

    @Override
    public List<Song> songOfSingerId(Long singerId) {
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.eq("singer_id", singerId);
        return songMapper.selectList(wrapper);
    }

    @Override
    public List<Song> songOfId(Long id) {
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return songMapper.selectList(wrapper);
    }

    @Override
    public List<Song> songOfSingerName(String name) {
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        return songMapper.selectList(wrapper);
    }
}
