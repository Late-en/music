package com.example.music.controller;

import com.example.music.entity.Comment;
import com.example.music.model.ApiResponse;
import com.example.music.model.request.CommentRequest;
import com.example.music.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Late-en
 */

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;


    /**
     * 提交评论
     */
    @PostMapping("/add")
    @ResponseBody
    public ApiResponse addComment(@RequestBody CommentRequest request) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(request, comment);
        if(commentService.addComment(comment)){
            return ApiResponse.ofSuccess("评论成功");
        } else return ApiResponse.ofError("评论失败");
    }

    /**
     * 删除评论
     */
    @GetMapping("/delete")
    @ResponseBody
    public ApiResponse deleteComment(@RequestParam Long id) {
        if(commentService.deleteComment(id)){
            return ApiResponse.ofSuccess("评论删除成功");
        } else return ApiResponse.ofError("评论删除失败");
    }

    /**
     * 获得指定歌曲 ID 的评论列表
     */
    @GetMapping("/song/detail")
    @ResponseBody
    public ApiResponse commentOfSongId(@RequestParam Long songId) {
        return ApiResponse.ofSuccess("评论返回成功", commentService.commentOfSongId(songId));
    }

    /**
     * 获得指定歌单 ID 的评论列表
     */
    @GetMapping("/songList/detail")
    @ResponseBody
    public ApiResponse commentOfSongListId(@RequestParam Long songListId) {
        return ApiResponse.ofSuccess("评论返回成功", commentService.commentOfSongListId(songListId));
    }

    /**
     * 点赞
     */
    @PostMapping("/like")
    @ResponseBody
    public synchronized ApiResponse commentOfLike(@RequestParam Long id) {
        if(commentService.commentUp(id)){
            return ApiResponse.ofSuccess("点赞成功");
        } else return ApiResponse.ofError("点赞失败");
    }
}
