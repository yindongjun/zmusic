package com.example.zmusic.service.impl;

import com.example.zmusic.dto.UserDto;
import com.example.zmusic.mapper.UserMapper;
import com.example.zmusic.repository.UserRepository;
import com.example.zmusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private UserMapper userMapper;

    @Override
    public List<UserDto> list() {
        return userMapper.toDto(userRepository.findAll());
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
