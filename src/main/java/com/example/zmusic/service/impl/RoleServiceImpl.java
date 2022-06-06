package com.example.zmusic.service.impl;

import com.example.zmusic.repository.UserRepository;
import com.example.zmusic.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private UserRepository userRepository;
    
}
