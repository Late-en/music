package com.example.music.controller;

import com.example.music.entity.SongList;
import com.example.music.model.ApiResponse;
import com.example.music.model.request.SongListRequest;
import com.example.music.service.SongListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Late-en
 */
@RestController
@RequestMapping("/songList")
@Slf4j
public class SongListController {
    @Autowired
    private SongListService songListService;


    /**
     * 添加歌单
     */
    @PostMapping("/add")
    @ResponseBody
    public ApiResponse addSongList(@RequestBody SongListRequest request) {
        SongList songList = new SongList();
        BeanUtils.copyProperties(request, songList);
        songList.setPic("/img/songListPic/123.jpg");
        if(songListService.addSongList(songList)){
            return ApiResponse.ofSuccess("歌单添加成功");
        } else return ApiResponse.ofError("歌单添加失败");
    }

    /**
     * 删除歌单
     */
    @GetMapping("/delete")
    @ResponseBody
    public ApiResponse deleteSongList(@RequestParam Long id) {
        if(songListService.deleteSongList(id)){
            return ApiResponse.ofSuccess("歌单删除成功");
        } else return ApiResponse.ofError("歌单删除失败");
    }

    /**
     * 这块就是前端显现相应的歌单list
     * 返回所有歌单
     */
    @GetMapping("")
    @ResponseBody
    public ApiResponse allSongList() {
        return ApiResponse.ofSuccess("歌单返回成功", songListService.allSongList());
    }

    /**
     * 返回标题包含文字的歌单
     */
    @GetMapping("/likeTitle/detail")
    @ResponseBody
    public ApiResponse songListOfLikeTitle(@RequestParam String title) {
        return ApiResponse.ofSuccess("歌单返回成功", songListService.likeTitle('%' + title + '%'));
    }

    /**
     * 返回指定类型的歌单
     */
    @GetMapping("/style/detail")
    @ResponseBody
    public ApiResponse songListOfStyle(@RequestParam String style) {
        return ApiResponse.ofSuccess("歌单返回成功", songListService.likeStyle('%' + style + '%'));
    }

    /**
     * 更新歌单信息
     */
    @PostMapping("/update/info")
    @ResponseBody
    public ApiResponse updateSongListInfo(@RequestBody SongListRequest request) {
        SongList songList = new SongList();
        BeanUtils.copyProperties(request, songList);
        if(songListService.updateSongListInfo(songList)){
            return ApiResponse.ofSuccess("歌单信息更新成功");
        } else return ApiResponse.ofError("歌单信息更新失败");
    }

    /**
     * 更新歌单图片
     */
    @PostMapping("/update/img")
    @ResponseBody
    public ApiResponse updateSongListPic(@RequestParam("file") MultipartFile avatarFile, @RequestParam("id") Long id) {
        if(songListService.updateSongListImg(avatarFile, id)){
            return ApiResponse.ofSuccess("歌单图片更新成功");
        } else return ApiResponse.ofError("歌单图片更新失败");
    }
}
