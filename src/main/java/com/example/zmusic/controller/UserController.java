package com.example.zmusic.controller;

import com.example.zmusic.mapper.UserMapper;
import com.example.zmusic.service.UserService;
import com.example.zmusic.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    private UserMapper userMapper;


    @GetMapping("/")
    public List<UserVo> list() {
        return userMapper.toVo(userService.list());
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
