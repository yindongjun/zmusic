package com.example.zmusic.controller;

import com.example.zmusic.dto.UserDto;
import com.example.zmusic.dto.UserSearchFilter;
import com.example.zmusic.mapper.UserMapper;
import com.example.zmusic.request.UserCreateRequest;
import com.example.zmusic.request.UserUpdateRequest;
import com.example.zmusic.request.UserUpdateRolesRequest;
import com.example.zmusic.service.UserService;
import com.example.zmusic.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Api(tags = "用户管理")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping("/{id}")
    @ApiOperation("根据ID获取用户信息")
    public UserVo get(@PathVariable String id) {
        UserDto userDto = userService.get(id);
        return userMapper.toVo(userDto);
    }

    @GetMapping("/me")
    @ApiOperation("获取当前用户信息")
    public UserVo getCurrentUser() {
        UserDto userDto = userService.getCurrentUser();
        return userMapper.toVo(userDto);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("查询用户列表")
    public Page<UserVo> search(@Validated UserSearchFilter filter) {
        Page<UserDto> page = userService.search(filter);
        return page.map(userMapper::toVo);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("创建用户")
    public UserVo create(@Validated @RequestBody UserCreateRequest userCreateRequest) {
        return userMapper.toVo(userService.create(userMapper.toDto(userCreateRequest)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("更新用户")
    public UserVo update(
            @PathVariable String id, @Validated @RequestBody UserUpdateRequest userUpdateRequest) {
        UserDto userDto = userService.update(id, userMapper.toDto(userUpdateRequest));
        return userMapper.toVo(userDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("删除用户")
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }

    @PutMapping("/{id}/lock")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("锁定用户")
    public void lock(@PathVariable String id) {
        userService.lock(id);
    }

    @PutMapping("/{id}/unlock")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("解锁用户")
    public void unlock(@PathVariable String id) {
        userService.unlock(id);
    }

    @PutMapping("/{id}/enable")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("启用用户")
    public void enable(@PathVariable String id) {
        userService.enable(id);
    }

    @PutMapping("/{id}/disable")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("禁用用户")
    public void disable(@PathVariable String id) {
        userService.disable(id);
    }

    @PutMapping("/{id}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("修改用户角色")
    public void updateRoles(
            @PathVariable String id,
            @Validated @RequestBody UserUpdateRolesRequest userUpdateRolesRequest) {
        userService.updateRoles(id, userUpdateRolesRequest.getRoleIds());
    }

    @PutMapping("/{id}/password")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("修改用户密码")
    public void updatePassword(
            @PathVariable String id,
            @Validated @RequestBody UserUpdatePasswordRequest userUpdatePasswordRequest) {
        userService.updatePassword(
                id,
                userUpdatePasswordRequest.getPassword(),
                userUpdatePasswordRequest.getConfirmPassword());
    }
}
