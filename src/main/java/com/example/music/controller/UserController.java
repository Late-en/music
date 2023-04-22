package com.example.music.controller;

import com.example.music.entity.User;
import com.example.music.model.ApiResponse;
import com.example.music.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Late-en
 */

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    /**
     * 用户注册
     */
    @PostMapping("/add")
    @ResponseBody
    public ApiResponse addUser(HttpServletRequest req){
        String username = req.getParameter("username").trim();
        if(userService.existUser(username)){
            return ApiResponse.ofWarning("用户已存在");
        }
        User user = User.builder().username(username)
                .password(req.getParameter("password").trim())
                .sex(new Integer(req.getParameter("sex").trim()))
                .phoneNum(req.getParameter("phoneNum").trim())
                .email(req.getParameter("email").trim())
                .introduction(req.getParameter("introduction").trim())
                .location(req.getParameter("location").trim())
                .avatar("/img/avatorImages/user.jpg").build();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            user.setBirth(dateFormat.parse(req.getParameter("birth").trim()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(!userService.addUser(user)){
            return ApiResponse.ofError("用户创建失败");
        }
        return ApiResponse.ofSuccess("用户创建成功");
    }

    /**
     * 登陆密码校验
     */
    @PostMapping("/login/status")
    @ResponseBody
    public ApiResponse loginStatus(HttpServletRequest req, HttpSession session){
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();

        if(userService.verityPasswd(username, password)){
            session.setAttribute("username", username);
            return ApiResponse.ofSuccess("登陆成功", userService.getUserInfo(username));
        }
        return ApiResponse.ofWarning("用户名或密码错误");
    }
}
