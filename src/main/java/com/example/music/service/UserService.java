package com.example.music.service;

import com.example.music.entity.User;
import java.util.List;

/**
 * @author Late-en
 */
public interface UserService {
    boolean addUser(User user);

    boolean existUser(String username);

    boolean deleteUser(User user);

    boolean updateUserMsg(User user);

    boolean updateUserAvatar(User user);

    boolean updatePassword(User user);

    /**
     * 校验密码是否正确
     */
    boolean verityPasswd(String username, String password);

    List<User> allUser();

    List<User> userOfId(Integer id);

    List<User> getUserInfo(String username);
}