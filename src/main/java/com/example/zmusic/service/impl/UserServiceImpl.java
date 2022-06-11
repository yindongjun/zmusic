package com.example.zmusic.service.impl;

import com.example.zmusic.constants.AuthenticationConfigConstants;
import com.example.zmusic.dto.LoginDto;
import com.example.zmusic.dto.UserDto;
import com.example.zmusic.entity.User;
import com.example.zmusic.exception.BizException;
import com.example.zmusic.exception.ExceptionType;
import com.example.zmusic.mapper.UserMapper;
import com.example.zmusic.repository.UserRepository;
import com.example.zmusic.request.TokenCreateRequest;
import com.example.zmusic.request.UserCreateRequest;
import com.example.zmusic.request.UserUpdateRequest;
import com.example.zmusic.service.UserService;
import com.example.zmusic.utils.IpUtils;
import com.example.zmusic.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    private User getEntityById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new BizException(ExceptionType.USER_NOT_FOUND));
    }

    private User getEntityByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new BizException(ExceptionType.USER_NOT_FOUND));
    }

    @Override
    public UserDto get(String id) {
        User user = getEntityById(id);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto getByUsername(String username) {
        return userMapper.toDto(getEntityByUsername(username));
    }

    @Override
    public Page<UserDto> search(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toDto);
    }

    @Override
    public List<UserDto> list() {
        return userMapper.toDto(userRepository.findAll());
    }

    @Override
    public UserDto create(UserCreateRequest userCreateRequest) {
        checkUsername(userCreateRequest.getUsername());

        User user = userMapper.createEntity(userCreateRequest);

        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        preInsert(user);
        User userCreated = userRepository.save(user);
        return userMapper.toDto(userCreated);
    }

    @Override
    public UserDto update(String id, UserUpdateRequest userUpdateRequest) {
        User user = getEntityById(id);
        userMapper.updateEntity(userUpdateRequest, user);

        User userSaved = userRepository.save(user);
        return userMapper.toDto(userSaved);
    }

    @Override
    public void delete(String id) {
        User user = getEntityById(id);
        userRepository.deleteById(user.getId());
    }

    private void preInsert(User user) {
        user.setEnabled(true);
        user.setLocked(false);
    }

    /**
     * 检查用户名是否重复
     *
     * @param username 用户名
     */
    private void checkUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            throw new BizException(ExceptionType.USERNAME_DUPLICATE);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.getEntityByUsername(username);
    }

    @Override
    public LoginDto login(TokenCreateRequest tokenCreateRequest, HttpServletRequest request) {
        User user = userRepository.findByUsername(tokenCreateRequest.getUsername())
                .orElseThrow(() -> new BizException(ExceptionType.USER_NOT_FOUND));

        // 用户被禁用
        if (user.disabled()) {
            throw new BizException(ExceptionType.USER_NOT_ENABLE);
        }

        // 用户被锁定
        if (user.locked()) {
            throw new BizException(ExceptionType.USER_LOCKED);
        }

        // 校验用户名和密码
        boolean isMatch = passwordEncoder.matches(tokenCreateRequest.getPassword(), user.getPassword());
        // 检验失败
        if (!isMatch) {
            throw new BizException(ExceptionType.USERNAME_PASSWORD_NOT_MATCH);
        }

        // 设置 ip 和 login time
        user.setLastLoginIp(IpUtils.getIpAddr(request));
        user.setLastLoginTime(LocalDateTime.now());

        // 更新用户的登录信息
        userRepository.save(user);

        // 生成 token
        String token = JwtUtils.generate(user.getUsername(),
                AuthenticationConfigConstants.EXPIRATION_TIME,
                AuthenticationConfigConstants.SECRET);

        // build token dto and return
        return new LoginDto(token);
    }

    @Override
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = this.getEntityByUsername(username);
        return userMapper.toDto(user);
    }
}
