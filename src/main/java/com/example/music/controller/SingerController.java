package com.example.music.controller;

import com.example.music.constant.Path;
import com.example.music.entity.Singer;
import com.example.music.model.ApiResponse;
import com.example.music.model.request.SingerRequest;
import com.example.music.service.SingerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Late-en
 */
@RestController
@RequestMapping("/singer")
public class SingerController {
    @Autowired
    private SingerService singerService;

    /**
     * 添加歌手
     */
    @PostMapping("/add")
    @ResponseBody
    public ApiResponse addSinger(@RequestBody SingerRequest request){
        if(singerService.existSinger(request.getName())){
            return ApiResponse.ofWarning("歌手已存在");
        }
        Singer singer = new Singer();
        BeanUtils.copyProperties(request, singer);
        singer.setPic("/img/avatarImages/user.jpg");
        if(!singerService.addSinger(singer)){
            return ApiResponse.ofError("歌手创建失败");
        }
        return ApiResponse.ofSuccess("歌手创建成功");
    }

    /**
     * 删除歌手
     */
    @GetMapping("/delete")
    @ResponseBody
    public ApiResponse deleteSinger(@RequestParam("id") Long id){
        if(singerService.deleteSinger(id)){
            return ApiResponse.ofSuccess("删除歌手成功");
        } else return ApiResponse.ofError("删除歌手失败");
    }

    /**
     * 更新歌手信息
     */
    @PostMapping("/update")
    @ResponseBody
    public ApiResponse updateSingerInfo(@RequestBody SingerRequest request){
        Singer singer = new Singer();
        BeanUtils.copyProperties(request, singer);
        if(singerService.updateSingerInfo(singer)){
            return ApiResponse.ofSuccess("修改歌手信息成功");
        } else return ApiResponse.ofError("修改歌手信息失败");
    }

    /**
     * 更新歌手图片
     */
    @PostMapping("/update/pic")
    @ResponseBody
    public ApiResponse updateSingerPic(@RequestParam("pic") MultipartFile pic, @RequestParam("id") Long id){
        String fileName = System.currentTimeMillis() + pic.getOriginalFilename();
        String filePath = Path.PROJECT_DATA_PATH + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "singerPic";
        //创建目录和本地文件
        File folders = new File(filePath);
        if(!folders.exists()) folders.mkdir();
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/singerPic/" + fileName;
        try {
            pic.transferTo(dest);
            Singer singer = Singer.builder().id(id).pic(imgPath).build();
            if(singerService.updateSingerPic(singer)){
                return ApiResponse.ofSuccess("歌手图片上传成功");
            } else return ApiResponse.ofError("歌手图片上传失败");
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResponse.ofError("歌手图片上传失败");
        }
    }

    /**
     * 返回所有歌手信息
     */
    @GetMapping("")
    @ResponseBody
    public ApiResponse allSinger(){
        return ApiResponse.ofSuccess("所有歌手信息返回成功", singerService.allSinger());
    }

    /**
     * 返回某个歌手信息
     */
    @GetMapping("/name/detail")
    @ResponseBody
    public ApiResponse singerOfName(@RequestParam("name") String name){
        return ApiResponse.ofSuccess("歌手"+name+"信息返回成功", singerService.SingerOfName(name));
    }

    /**
     * 返回所有男/女歌手信息
     */
    @GetMapping("/sex/detail")
    @ResponseBody
    public ApiResponse singerOfSex(@RequestParam("sex") Integer sex){
        return ApiResponse.ofSuccess("所有性别"+sex+"歌手信息返回成功", singerService.SingerOfSex(sex));
    }

}
