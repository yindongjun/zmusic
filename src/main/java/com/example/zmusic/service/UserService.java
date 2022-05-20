package com.example.zmusic.service;

import com.example.zmusic.request.UserCreateRequest;
import com.example.zmusic.dto.UserDto;
import com.example.zmusic.dto.UserLoginDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserDto> list();

    UserDto create(UserCreateRequest userCreateRequest);

    UserDto login(UserLoginDto userLoginDto, HttpServletRequest request);
}
