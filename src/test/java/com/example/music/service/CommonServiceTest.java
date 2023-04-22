package com.example.music.service;
import cn.hutool.core.lang.Assert;
import com.example.music.MusicApplicationTests;
import com.example.music.entity.User;
import com.example.music.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Late-en
 */
@Slf4j
public class CommonServiceTest extends MusicApplicationTests {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testAddUser(){
        User test = User.builder().username("test1").password("123").build();
        boolean res = userService.addUser(test);
        Assert.isTrue(res);
        log.debug("【测试id回显#test.getId()】= {}", test.getId());
    }
}
