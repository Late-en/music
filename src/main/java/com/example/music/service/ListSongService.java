package com.example.music.service;

import com.example.music.entity.ListSong;

import java.util.List;

/**
 * @author Late-en
 */
public interface ListSongService {
    boolean addListSong(ListSong listSong);

    boolean updateListSongInfo(ListSong listSong);

    boolean deleteListSong(Long songId, Long songListId);

    List<ListSong> allListSong();

    List<Long> SongsOfSongList(Long songListId);
}
