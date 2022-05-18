package com.example.zmusic.controller;

import com.example.zmusic.constants.AuthenticationConfigConstants;
import com.example.zmusic.dto.UserDto;
import com.example.zmusic.dto.UserLoginDto;
import com.example.zmusic.mapper.UserMapper;
import com.example.zmusic.service.UserService;
import com.example.zmusic.utils.JwtUtils;
import com.example.zmusic.vo.AuthLoginVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping("/login")
    public AuthLoginVo login(@RequestBody UserLoginDto userLoginDto, HttpServletRequest request) {
        UserDto userDto = userService.login(userLoginDto, request);
        String token = JwtUtils.generate(userDto.getUsername(),null, AuthenticationConfigConstants.EXPIRATION_TIME, AuthenticationConfigConstants.SECRET);
        return new AuthLoginVo(userMapper.toVo(userDto), token);
    }
}