package com.example.music.controller;

import com.example.music.constant.Path;
import com.example.music.entity.User;
import com.example.music.model.ApiResponse;
import com.example.music.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
                .location(req.getParameter("location").trim()).build();
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

    /**
     * 返回所有用户
     */
    @GetMapping("")
    @ResponseBody
    public ApiResponse allUser() {
        return ApiResponse.ofSuccess("所有用户信息返回成功", userService.allUser());
    }

    /**
     * 根据id返回用户
     */
    @GetMapping("/detail")
    @ResponseBody
    public ApiResponse userOfId(HttpServletRequest req){
        Integer id = Integer.parseInt(req.getParameter("id").trim());
        return ApiResponse.ofSuccess("用户"+id+"信息返回成功", userService.userOfId(id));
    }

    /**
     * 更新用户信息数据
     */
    @PostMapping("/update")
    @ResponseBody
    public ApiResponse updateUserInfo(HttpServletRequest req){
        User user = User.builder()
                .id(Long.parseLong(req.getParameter("id").trim()))
                .sex(new Integer(req.getParameter("sex").trim()))
                .phoneNum(req.getParameter("phoneNum").trim())
                .email(req.getParameter("email").trim())
                .introduction(req.getParameter("introduction").trim())
                .location(req.getParameter("location").trim())
                .updateTime(new Date()).build();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            user.setBirth(dateFormat.parse(req.getParameter("birth").trim()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String username = req.getParameter("username").trim();
        if(!userService.existUser(username)) user.setUsername(username);
        if(!userService.updateUserInfo(user)){
            return ApiResponse.ofError("用户修改失败");
        }
        return ApiResponse.ofSuccess("用户修改成功");
    }

    /**
     * 更新用户头像
     */
    @PostMapping("/update/avatar")
    @ResponseBody
    public ApiResponse updateUserAvatar(@RequestParam("avatar") MultipartFile avatar, @RequestParam("id") Long id){
        String fileName = System.currentTimeMillis() + avatar.getOriginalFilename();
        String filePath = Path.PROJECT_DATA_PATH + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "avatarImages";
        //创建目录和本地文件
        File folders = new File(filePath);
        if(!folders.exists()) folders.mkdir();
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/avatarImages/" + fileName;
        try {
            avatar.transferTo(dest);
            User user = User.builder().id(id).avatar(imgPath).build();
            if(userService.updateUserAvatar(user)){
                return ApiResponse.ofSuccess("头像上传成功");
            } else return ApiResponse.ofError("头像上传失败");
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResponse.ofError("头像上传失败");
        }
    }

    /**
     * 更改用户密码
     */
    @PostMapping("/update/password")
    @ResponseBody
    public ApiResponse updateUserPassword(HttpServletRequest req){
        String username = req.getParameter("username").trim();
        String oldPassword = req.getParameter("old_password").trim();
        Long id = Long.parseLong(req.getParameter("id").trim());
        if(!userService.verityPasswd(username, oldPassword)){
            return ApiResponse.ofWarning("原始密码错误");
        }
        String password = req.getParameter("password").trim();
        User user = User.builder().id(id).password(password).build();
        if(userService.updatePassword(user)){
            return ApiResponse.ofSuccess("密码修改成功");
        } else return ApiResponse.ofError("未知错误，密码修改失败");
//        String newPasswd = req.getParameter("newPasswd").trim();
//        String newPasswd2 = req.getParameter("newPasswd2").trim();
//        if(newPasswd.equals(newPasswd2)){
//            if(userService.updatePassword(username, newPasswd)){
//                return ApiResponse.ofSuccess("密码修改成功");
//            } else return ApiResponse.ofError("未知错误，密码修改失败");
//        } else return ApiResponse.ofWarning("两次新密码输入不一致");
    }

    /**
     * 删除用户
     */
    @GetMapping("/delete")
    @ResponseBody
    public ApiResponse deleteUser(HttpServletRequest req){
        Long id = Long.parseLong(req.getParameter("id").trim());
        if(userService.deleteUser(id)){
            return ApiResponse.ofSuccess("用户删除成功");
        } else return ApiResponse.ofError("用户删除失败");
    }
}