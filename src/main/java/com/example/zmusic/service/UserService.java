package com.example.zmusic.service;

import com.example.zmusic.dto.TokenDto;
import com.example.zmusic.dto.UserDto;
import com.example.zmusic.request.TokenCreateRequest;
import com.example.zmusic.request.UserCreateRequest;
import com.example.zmusic.request.UserUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService extends UserDetailsService {

    UserDto get(String id);

    Page<UserDto> search(Pageable pageable);

    List<UserDto> list();

    UserDto create(UserCreateRequest userCreateRequest);

    UserDto update(String id, UserUpdateRequest userUpdateRequest);

    void delete(String id);

    TokenDto createToken(TokenCreateRequest tokenCreateRequest, HttpServletRequest request);

    UserDto getCurrentUser();
}
