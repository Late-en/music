package com.example.music.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.music.entity.Collect;

import java.util.List;

/**
 * @author Late-en
 */
public interface CollectService {
    boolean addCollection(Collect collect);

    boolean existCollection(Long userId, Long songOrListId, Integer type);

    boolean deleteCollection(Long userId,Long songOrListId, Integer type);

    List<Collect> collectionOfUser(Long userId);
}
