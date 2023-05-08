package com.example.music.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.music.entity.Comment;
import com.example.music.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Late-en
 */

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public boolean addComment(Comment comment) {
        return commentMapper.insert(comment) == 1;
    }

    @Override
    public boolean deleteComment(Long commentId) {
        return commentMapper.deleteById(commentId) == 1;
    }

    @Override
    public boolean commentUp(Long id) {
        UpdateWrapper<Comment> wrapper = new UpdateWrapper<>();
        wrapper.setSql("up = up + 1").eq("id", id);
        return commentMapper.update(null, wrapper) == 1;
    }

    @Override
    public List<Comment> commentOfSongId(Long songId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("song_id", songId);
        return commentMapper.selectList(wrapper);
    }
    @Override
    public List<Comment> commentOfSongListId(Long songListId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("song_list_id", songListId);
        return commentMapper.selectList(wrapper);
    }
}
