package com.example.music.controller;

import com.example.music.entity.Collect;
import com.example.music.model.ApiResponse;
import com.example.music.model.request.CollectRequest;
import com.example.music.service.CollectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Late-en
 */

@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;

    /**
     * 添加收藏的歌曲/单
     */
    @PostMapping("/add")
    @ResponseBody
    public ApiResponse addCollection(@RequestBody CollectRequest request) {
        Long songOrListId = request.getType() == 0 ? request.getSongId() : request.getSongListId();
        if(collectService.existCollection(request.getUserId(), songOrListId, request.getType()))
            return ApiResponse.ofWarning("该歌曲/单已被收藏");
        Collect collect = new Collect();
        BeanUtils.copyProperties(request, collect);
        if(collectService.addCollection(collect)){
            return ApiResponse.ofSuccess("收藏成功");
        } else return ApiResponse.ofError("收藏失败");
    }

    /**
     * 取消收藏的歌曲/单
     */
    @PostMapping("/delete")
    @ResponseBody
    public ApiResponse deleteCollection(@RequestBody CollectRequest request) {
        Long songOrListId = request.getType() == 0 ? request.getSongId() : request.getSongListId();
        if(collectService.deleteCollection(request.getUserId(), songOrListId, request.getType())){
            return ApiResponse.ofSuccess("收藏删除成功");
        } else return ApiResponse.ofError("收藏删除失败");
    }

    /**
     * 返回指定用户 ID 收藏的列表
     */
    @GetMapping("/detail")
    @ResponseBody
    public ApiResponse collectionOfUser(@RequestParam Long userId) {
        return ApiResponse.ofSuccess("收藏返回成功", collectService.collectionOfUser(userId));
    }
}
