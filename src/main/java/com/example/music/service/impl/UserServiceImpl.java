package com.example.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.music.mapper.UserMapper;
import com.example.music.entity.User;
import com.example.music.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Late-en
 */
@Slf4j
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
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return userMapper.selectCount(wrapper) == 1;
    }

    @Override
    public boolean deleteUser(Serializable id) {
        return userMapper.deleteById(id) == 1;
    }

    @Override
    public boolean updateUserInfo(User user) {
        boolean temp =  userMapper.updateUserInfo(user) == 1;
        log.debug(String.valueOf(temp));
        return temp;
    }

    @Override
    public boolean updateUserAvatar(User user) {
        return userMapper.updateById(user) == 1;
    }

    @Override
    public boolean updatePassword(User user) {
        return userMapper.updateById(user) == 1;
    }

    @Override
    public boolean verityPasswd(String username, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.eq("password", password);
        return userMapper.selectCount(wrapper) == 1;
    }

    @Override
    public List<User> allUser() {
        return userMapper.selectList(null);
    }

    @Override
    public List<User> userOfId(Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return userMapper.selectByMap(params);
    }

    @Override
    public List<User> getUserInfo(String username) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        return userMapper.selectByMap(params);
    }
}
