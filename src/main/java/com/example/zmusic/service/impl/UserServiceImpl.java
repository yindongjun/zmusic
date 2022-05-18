package com.example.zmusic.service.impl;

import com.example.zmusic.dto.UserCreateDto;
import com.example.zmusic.dto.UserDto;
import com.example.zmusic.dto.UserLoginDto;
import com.example.zmusic.entity.User;
import com.example.zmusic.exception.BizException;
import com.example.zmusic.exception.ExceptionType;
import com.example.zmusic.mapper.UserMapper;
import com.example.zmusic.repository.UserRepository;
import com.example.zmusic.service.UserService;
import com.example.zmusic.utils.IpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> list() {
        return userMapper.toDto(userRepository.findAll());
    }

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        checkUsername(userCreateDto.getUsername());

        User user = userMapper.toDo(userCreateDto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        preInsert(user);
        User userCreated = userRepository.save(user);

        return userMapper.toDto(userCreated);
    }

    private void preInsert(User user) {
        user.setEnabled(true);
        user.setLocked(false);
    }

    private void checkUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            throw new BizException(ExceptionType.USERNAME_DUPLICATE);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new BizException(ExceptionType.USER_NOT_FOUND));
    }

    @Override
    public UserDto login(UserLoginDto userLoginDto, HttpServletRequest request) {
        User user = userRepository.findByUsername(userLoginDto.getUsername())
                .orElseThrow(() -> new BizException(ExceptionType.USER_NOT_FOUND));
        boolean isMatch = passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword());
        // 检验失败
        if (isMatch) {
            throw new BizException(ExceptionType.USERNAME_PWD_ERROR);
        }

        // 设置 ip 和 login time
        user.setLastLoginIp(IpUtils.getIpAddr(request));
        user.setLastLoginTime(LocalDateTime.now());

        userRepository.save(user);

        return userMapper.toDto(user);
    }
}
