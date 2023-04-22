package com.example.music.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.music.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where username = #{username}")
    List<User> selectByUsername(@Param("username") String username);

    @Select("select count(*) from user where username = #{username} and password = #{password}")
    Integer verityPasswd(@Param("username") String username, @Param("password") String password);
}