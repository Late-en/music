package com.example.music.model.request;

import lombok.Data;

/**
 * @author Late-en
 */

@Data
public class CollectRequest {
    private Long userId;

    private Integer type;

    private Long songId;

    private Long songListId;
}
