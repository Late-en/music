package com.example.music.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.music.entity.SongList;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Late-en
 */
public interface SongListService {
    boolean addSongList(SongList songList);

    boolean updateSongListInfo(SongList songList);

    boolean updateSongListImg(MultipartFile avatarFile, Long id);

    boolean deleteSongList(Long id);

    List<SongList> allSongList();

    List<SongList> likeTitle(String title);

    List<SongList> likeStyle(String style);
}
