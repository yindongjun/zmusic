package com.example.zmusic.controller;

import com.example.zmusic.request.UserCreateRequest;
import com.example.zmusic.mapper.UserMapper;
import com.example.zmusic.service.UserService;
import com.example.zmusic.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public List<UserVo> list() {
        return userMapper.toVo(userService.list());
    }

    @PostMapping("/")
    public UserVo createUser(@Validated @RequestBody UserCreateRequest userCreateRequest) {
        return userMapper.toVo(userService.create(userCreateRequest));
    }
}
