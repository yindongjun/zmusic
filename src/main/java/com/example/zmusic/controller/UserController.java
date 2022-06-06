package com.example.zmusic.controller;

import com.example.zmusic.dto.UserDto;
import com.example.zmusic.mapper.UserMapper;
import com.example.zmusic.request.UserCreateRequest;
import com.example.zmusic.request.UserUpdateRequest;
import com.example.zmusic.service.UserService;
import com.example.zmusic.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public Page<UserVo> search(@PageableDefault(sort = {"createdTime"}, direction = Sort.Direction.ASC)
                               Pageable pageable) {
        Page<UserDto> page = userService.search(pageable);
        return page.map(userMapper::toVo);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("创建用户")
    public UserVo create(@Validated @RequestBody UserCreateRequest userCreateRequest) {
        return userMapper.toVo(userService.create(userCreateRequest));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("更新用户")
    public UserVo update(@PathVariable String id, @Validated @RequestBody UserUpdateRequest userUpdateRequest) {
        UserDto userDto = userService.update(id, userUpdateRequest);
        return userMapper.toVo(userDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("删除用户")
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }
}
