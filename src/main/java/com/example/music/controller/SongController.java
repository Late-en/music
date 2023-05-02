package com.example.music.controller;

import com.example.music.entity.Song;
import com.example.music.model.ApiResponse;
import com.example.music.service.impl.SongServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Late-en
 */
@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    private SongServiceImpl songService;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 文件最大10M,DataUnit提供5中类型B,KB,MB,GB,TB
        factory.setMaxFileSize(DataSize.of(20, DataUnit.MEGABYTES));
        // 设置总上传数据总大小10M
        factory.setMaxRequestSize(DataSize.of(20, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }

    /**
     * 添加歌曲
     */
    @PostMapping("/add")
    @ResponseBody
    public ApiResponse addSong(HttpServletRequest req, @RequestParam("file") MultipartFile songFile){
        Song song = Song.builder().name(req.getParameter("name").trim())
                .singerId(Long.parseLong(req.getParameter("singerId").trim()))
                .introduction(req.getParameter("introduction").trim())
                .lyric(req.getParameter("lyric").trim()).build();
        song.setPic("/img/songPic/common.jpg");
        song.setUrl("/song/" + songFile.getOriginalFilename());
        if(songService.addSong(songFile, song)){
            return ApiResponse.ofSuccess("歌曲添加成功");
        } else return ApiResponse.ofError("歌曲添加失败");
    }

    /**
     * 更新歌曲图片
     */
    @PostMapping("/update/pic")
    @ResponseBody
    public ApiResponse updateSongPic(@RequestParam("pic") MultipartFile picFile, @RequestParam("id") Long id){
        if(songService.updateSongPic(picFile, id)){
            return ApiResponse.ofSuccess("歌曲图片更新成功");
        } else return ApiResponse.ofError("歌曲图片更新失败");
    }

    /**
     * 更新歌曲文件
     */
    @PostMapping("/update/url")
    @ResponseBody
    public ApiResponse updateSongUrl(@RequestParam("song") MultipartFile song, @RequestParam("id") Long id){
        if(songService.updateSongUrl(song, id)){
            return ApiResponse.ofSuccess("歌曲文件更新成功");
        } else return ApiResponse.ofError("歌曲文件更新失败");
    }

    /**
     * 更新歌曲信息
     */
    @PostMapping("/update/info")
    @ResponseBody
    public ApiResponse updateSongInfo(HttpServletRequest req){
        Song song = Song.builder().name(req.getParameter("name").trim()).
                id(Long.parseLong(req.getParameter("id").trim())).
                singerId(Long.parseLong(req.getParameter("singerId").trim())).
                introduction(req.getParameter("introduction").trim()).
                lyric(req.getParameter("lyric").trim()).build();
        if(songService.updateSongInfo(song)){
            return ApiResponse.ofSuccess("歌曲信息更新成功");
        } else return ApiResponse.ofError("歌曲信息更新失败");
    }

    /**
     * 删除歌曲
     */
    @GetMapping("/delete")
    @ResponseBody
    public ApiResponse deleteSong(@RequestParam("id") Long id){
        if(songService.deleteSong(id)){
            return ApiResponse.ofSuccess("歌曲删除成功");
        } else return ApiResponse.ofError("歌曲删除失败");
    }

    /**
     * 返回所有歌曲
     */
    @GetMapping("")
    @ResponseBody
    public ApiResponse allSong(){
        return ApiResponse.ofSuccess("所有歌曲返回成功", songService.allSong());
    }

    /**
     * 根据歌手id返回其所有歌曲
     */
    @GetMapping("/singer/detail")
    @ResponseBody
    public ApiResponse songOfSingerId(@RequestParam("singerId") Long singerId){
        return ApiResponse.ofSuccess("歌曲返回成功", songService.songOfSingerId(singerId));
    }

    /**
     * 根据id返回歌曲
     */
    @GetMapping("/detail")
    @ResponseBody
    public ApiResponse songOfId(@RequestParam("id") Long id){
        return ApiResponse.ofSuccess("歌曲返回成功", songService.songOfId(id));
    }

    /**
     * 根据歌手名返回所有歌曲
     */
    @GetMapping("/singer")
    @ResponseBody
    public ApiResponse songOfId(@RequestParam("name") String singerName){
        return ApiResponse.ofSuccess("歌曲返回成功", songService.songOfSingerName(singerName));
    }
}
