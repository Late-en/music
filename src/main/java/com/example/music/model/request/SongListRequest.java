package com.example.music.model.request;

import lombok.Data;

/**
 * @author Late-en
 */
@Data
public class SongListRequest {
    private Long id;

    private String title;

    private String pic;

    private String introduction;

    private String style;
}
