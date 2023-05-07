package com.example.music.controller;

import com.example.music.entity.ListSong;
import com.example.music.model.ApiResponse;
import com.example.music.model.request.ListSongRequest;
import com.example.music.service.ListSongService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Late-en
 */

@RestController
@RequestMapping("/listSong")
public class ListSongController {
    @Autowired
    private ListSongService listSongService;


    /**
     * 给歌单添加歌曲
     */
    @PostMapping("/add")
    @ResponseBody
    public ApiResponse addListSong(@RequestBody ListSongRequest request) {
        ListSong listSong = new ListSong();
        BeanUtils.copyProperties(request, listSong);
        if(listSongService.addListSong(listSong)){
            return ApiResponse.ofSuccess("歌单歌曲添加成功");
        } else return ApiResponse.ofError("歌单歌曲添加失败");
    }

    /**
     * 删除歌单里的歌曲
     */
    @GetMapping("/delete")
    @ResponseBody
    public ApiResponse deleteListSong(@RequestParam Long songId, @RequestParam Long songListId) {
        if(listSongService.deleteListSong(songId, songListId)){
            return ApiResponse.ofSuccess("歌单歌曲删除成功");
        } else return ApiResponse.ofError("歌单歌曲删除失败");
    }

    /**
     * 返回歌单里指定歌单 ID 的歌曲
     */
    @GetMapping("/detail")
    @ResponseBody
    public ApiResponse listSongOfSongId(@RequestParam Long songListId) {
        return ApiResponse.ofSuccess("歌单歌曲返回成功", listSongService.SongsOfSongList(songListId));
    }

    /**
     * 更新歌单里面的歌曲信息
     */
    @PostMapping("/update/Info")
    @ResponseBody
    public ApiResponse updateListSongInfo(@RequestBody ListSongRequest request) {
        ListSong listSong = new ListSong();
        BeanUtils.copyProperties(request, listSong);
        if(listSongService.updateListSongInfo(listSong)){
            return ApiResponse.ofSuccess("歌单信息修改成功");
        } else return ApiResponse.ofError("歌单信息修改失败");
    }
}
