package com.example.music.model.request;

import lombok.Data;

/**
 * @author Late-en
 */

@Data
public class CommentRequest {
    private Long userId;

    private Long songId;

    private Long songListId;

    private String content;

    private Integer type;

    private Long up;
}
