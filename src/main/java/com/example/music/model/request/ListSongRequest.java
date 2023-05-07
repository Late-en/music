package com.example.music.model.request;

import lombok.Data;

/**
 * @author Late-en
 */
@Data
public class ListSongRequest {
    private Long id;

    private Long songId;

    private Long songListId;
}
