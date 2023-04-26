package com.example.music.controller;

import com.example.music.entity.Singer;
import com.example.music.entity.User;
import com.example.music.model.ApiResponse;
import com.example.music.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Late-en
 */
@RestController
@RequestMapping("/singer")
public class SingerController {
    @Autowired
    private SingerService singerService;

    @PostMapping("/add")
    @ResponseBody
    public ApiResponse addSinger(HttpServletRequest req){
        String name = req.getParameter("name").trim();
        Singer singer = Singer.builder().name(name)
                .sex(new Integer(req.getParameter("sex").trim()))
                .pic(req.getParameter("pic").trim())
                .introduction(req.getParameter("introduction").trim())
                .location(req.getParameter("location").trim()).build();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            singer.setBirth(dateFormat.parse(req.getParameter("birth").trim()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(!singerService.addSinger(singer)){
            return ApiResponse.ofError("用户创建失败");
        }
        return ApiResponse.ofSuccess("用户创建成功");
    }
}
