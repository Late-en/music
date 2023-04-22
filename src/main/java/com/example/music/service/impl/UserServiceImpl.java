package com.example.music.service.impl;

import com.example.music.mapper.UserMapper;
import com.example.music.entity.User;
import com.example.music.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Late-en
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public boolean addUser(User user) {
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean existUser(String username) {
        return userMapper.selectByUsername(username) == null;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    @Override
    public boolean updateUserMsg(User user) {
        return false;
    }

    @Override
    public boolean updateUserAvatar(User user) {
        return false;
    }

    @Override
    public boolean updatePassword(User user) {
        return false;
    }

    @Override
    public boolean verityPasswd(String username, String password) {
        return userMapper.verityPasswd(username, password) == 1;
    }

    @Override
    public List<User> allUser() {
        return null;
    }

    @Override
    public List<User> userOfId(Integer id) {
        return null;
    }

    @Override
    public List<User> getUserInfo(String username) {
        return userMapper.selectByUsername(username);
    }
}
