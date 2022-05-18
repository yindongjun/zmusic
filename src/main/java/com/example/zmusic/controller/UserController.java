package com.example.zmusic.controller;

import com.example.zmusic.dto.UserCreateDto;
import com.example.zmusic.mapper.UserMapper;
import com.example.zmusic.service.UserService;
import com.example.zmusic.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping("/")
    public List<UserVo> list() {
        return userMapper.toVo(userService.list());
    }

    @PostMapping("/")
    public UserVo createUser(@RequestBody UserCreateDto userCreateDto) {
        return userMapper.toVo(userService.create(userCreateDto));
    }
}
