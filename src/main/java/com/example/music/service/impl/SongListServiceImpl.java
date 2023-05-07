package com.example.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.music.constant.Path;
import com.example.music.entity.SongList;
import com.example.music.mapper.SongListMapper;
import com.example.music.service.SongListService;
import com.example.music.utils.fileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * @author Late-en
 */

@Service
public class SongListServiceImpl implements SongListService {
    @Autowired
    private SongListMapper songListMapper;

    @Override
    public boolean addSongList(SongList songList) {
        return songListMapper.insert(songList) == 1;
    }

    @Override
    public boolean updateSongListInfo(SongList songList) {
        return songListMapper.updateById(songList) == 1;
    }

    @Override
    public boolean updateSongListImg(MultipartFile picFile, Long id) {
        String fileName = System.currentTimeMillis() + picFile.getOriginalFilename();
        File dest = new File(Path.IMAGE_SONG_LIST_PATH + System.getProperty("file.separator") + fileName);
        SongList songList = SongList.builder().id(id).pic("/img/songListPic/" + fileName).build();
        return fileUtils.fileTrans(picFile, dest, songListMapper, songList);
    }

    @Override
    public boolean deleteSongList(Long id) {
        return songListMapper.deleteById(id) == 1;
    }

    @Override
    public List<SongList> allSongList() {
        return songListMapper.selectList(null);
    }

    @Override
    public List<SongList> likeTitle(String title) {
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        wrapper.like("title", title);
        return songListMapper.selectList(wrapper);
    }

    @Override
    public List<SongList> likeStyle(String style) {
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        wrapper.like("style", style);
        return songListMapper.selectList(wrapper);
    }
}
