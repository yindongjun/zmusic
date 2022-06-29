package com.example.zmusic.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.example.zmusic.constants.AuthenticationConfigConstants;
import com.example.zmusic.dto.LoginDto;
import com.example.zmusic.dto.UserDto;
import com.example.zmusic.dto.UserSearchFilter;
import com.example.zmusic.entity.User;
import com.example.zmusic.exception.BizException;
import com.example.zmusic.exception.ExceptionType;
import com.example.zmusic.mapper.MapperInterface;
import com.example.zmusic.mapper.UserMapper;
import com.example.zmusic.repository.RoleRepository;
import com.example.zmusic.repository.UserRepository;
import com.example.zmusic.repository.specs.SearchCriteria;
import com.example.zmusic.repository.specs.SearchOperation;
import com.example.zmusic.repository.specs.UserSpecification;
import com.example.zmusic.request.TokenCreateRequest;
import com.example.zmusic.service.UserService;
import com.example.zmusic.utils.IpUtils;
import com.example.zmusic.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl extends SimpleGeneralServiceImpl<User, UserDto>
    implements UserService {

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final UserMapper userMapper;

  private final PasswordEncoder passwordEncoder;

  private User getEntityByUsername(String username) {
    return userRepository
        .findByUsername(username)
        .orElseThrow(() -> new BizException(ExceptionType.USER_NOT_FOUND));
  }

  @Override
  public UserDto getByUsername(String username) {
    return userMapper.toDto(getEntityByUsername(username));
  }

  @Override
  public Page<UserDto> search(UserSearchFilter filter) {
    Pageable pageable = filter.toPageable();
    UserSpecification specification = new UserSpecification();

    // build specification
    if (StrUtil.isNotBlank(filter.getUsername())) {
      specification.add(
          new SearchCriteria("username", filter.getUsername(), SearchOperation.MATCH));
    }

    return userRepository.findAll(specification, pageable).map(userMapper::toDto);
  }

  @Override
  @Transactional
  public UserDto create(UserDto userDto) {
    // 校验用户名是否重复
    checkUsername(userDto);

    // 校验密码和确认密码是否一致
    checkConfirmPassword(userDto);

    // encode password
    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

    // preInsert
    userDto.setEnabled(true);
    userDto.setLocked(false);

    return super.create(userDto);
  }

  private void checkConfirmPassword(UserDto userDto) {
    if (ObjectUtil.notEqual(userDto.getPassword(), userDto.getConfirmPassword())) {
      throw new BizException(ExceptionType.CONFIRM_PASSWORD_NOT_MATCH);
    }
  }

  @Override
  @Transactional
  public void delete(String id) {
    User user = getEntity(id);
    userRepository.deleteById(user.getId());
  }

  /**
   * 检查用户名是否重复
   *
   * @param userDto 用户信息
   */
  private void checkUsername(UserDto userDto) {
    String realId = Optional.ofNullable(userDto.getId()).orElse(StrUtil.EMPTY);
    userRepository
        .findByUsername(userDto.getUsername())
        .ifPresent(
            oldUser -> {
              if (ObjectUtil.notEqual(oldUser.getId(), realId)) {
                throw new BizException(ExceptionType.USERNAME_DUPLICATE);
              }
            });
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.getEntityByUsername(username);
  }

  @Override
  public LoginDto login(TokenCreateRequest tokenCreateRequest, HttpServletRequest request) {
    User user =
        userRepository
            .findByUsername(tokenCreateRequest.getUsername())
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
    String token =
        JwtUtils.generate(
            user.getUsername(),
            AuthenticationConfigConstants.EXPIRATION_TIME,
            AuthenticationConfigConstants.SECRET);

    // build token dto and return
    return new LoginDto(token, user.getId());
  }

  @Override
  public UserDto getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    User user = this.getEntityByUsername(username);
    return userMapper.toDto(user);
  }

  @Override
  @Transactional
  public void lock(String id) {
    User user = getEntity(id);
    user.setLocked(true);
    userRepository.save(user);
  }

  @Override
  @Transactional
  public void unlock(String id) {
    User user = getEntity(id);
    user.setLocked(false);
    userRepository.save(user);
  }

  @Override
  @Transactional
  public void enable(String id) {
    User user = getEntity(id);
    user.setEnabled(true);
    userRepository.save(user);
  }

  @Override
  @Transactional
  public void disable(String id) {
    User user = getEntity(id);
    user.setEnabled(false);
    userRepository.save(user);
  }

  @Override
  @Transactional
  public void updateRoles(String id, List<String> roleIds) {
    User user = getEntity(id);
    user.setRoles(roleRepository.findAllById(roleIds));
    userRepository.save(user);
  }

  @Override
  public void updatePassword(String id, String password, String confirmPassword) {
    if (ObjectUtil.notEqual(password, confirmPassword)) {
      throw new BizException(ExceptionType.CONFIRM_PASSWORD_NOT_MATCH);
    }
    User user = getEntity(id);
    user.setPassword(passwordEncoder.encode(password));
  }

  @Override
  public MapperInterface<User, UserDto> getMapstructMapper() {
    return this.userMapper;
  }

  @Override
  public JpaRepository<User, String> getRepository() {
    return userRepository;
  }

  @Override
  public BizException getNotFoundException() {
    return new BizException(ExceptionType.USER_NOT_FOUND);
  }
}
