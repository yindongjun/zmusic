package com.example.zmusic.service.impl;

import com.example.zmusic.repository.UserRepository;
import com.example.zmusic.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {
    private UserRepository userRepository;
}
