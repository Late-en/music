package com.example.music.service;

import com.example.music.entity.Song;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Late-en
 */
public interface SongService {
    boolean addSong (MultipartFile songFile, Song song);

    boolean updateSongInfo(Song song);

    boolean updateSongUrl(MultipartFile songFile, Long id);

    boolean updateSongPic(MultipartFile picFile, Long id);

    boolean deleteSong(Long id);

    List<Song> allSong();

    List<Song> songOfSingerId(Long singerId);

    List<Song> songOfId(Long id);

    List<Song> songOfSingerName(String name);
}
