package com.example.music.service;

import com.example.music.entity.Comment;

import java.util.List;

/**
 * @author Late-en
 */
public interface CommentService {
    boolean addComment(Comment comment);

    boolean deleteComment(Long id);

    boolean commentUp(Long id);

    List<Comment> commentOfSongId(Long songId);

    List<Comment> commentOfSongListId(Long songListId);
}
