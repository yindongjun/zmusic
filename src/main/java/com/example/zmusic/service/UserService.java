package com.example.zmusic.service;

import com.example.zmusic.dto.LoginDto;
import com.example.zmusic.dto.UserDto;
import com.example.zmusic.dto.UserSearchFilter;
import com.example.zmusic.entity.User;
import com.example.zmusic.request.TokenCreateRequest;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService extends UserDetailsService, GeneralService<User, UserDto> {

    UserDto getByUsername(String username);

    Page<UserDto> search(UserSearchFilter filter);

    LoginDto login(TokenCreateRequest tokenCreateRequest, HttpServletRequest request);

    UserDto getCurrentUser();

    void lock(String id);

    void unlock(String id);

    void enable(String id);

    void disable(String id);

    void updateRoles(String id, List<String> roleIds);

    void updatePassword(String id, String password, String confirmPassword);
}
