package com.example.music.service;

import com.example.music.entity.Singer;

import java.io.Serializable;
import java.util.List;

/**
 * @author Late-en
 */
public interface SingerService {
    boolean addSinger(Singer singer);

    boolean existSinger(String name);

    boolean deleteSinger(Serializable id);

    boolean updateSingerInfo(Singer Singer);

    boolean updateSingerPic(Singer Singer);

    List<Singer> allSinger();

    List<Singer> SingerOfName(String name);

    List<Singer> SingerOfSex(Integer sex);
}
