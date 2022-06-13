package com.example.zmusic.mapper;

import com.example.zmusic.dto.UserDto;
import com.example.zmusic.entity.User;
import com.example.zmusic.request.UserCreateRequest;
import com.example.zmusic.request.UserUpdateRequest;
import com.example.zmusic.vo.UserVo;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {
    UserDto toDto(User user);


    UserVo toVo(UserDto userDto);

    List<UserDto> toDto(List<User> users);

    List<UserVo> toVo(List<UserDto> userDtos);

    User toEntity(UserDto userDto);

    User createEntity(UserCreateRequest userCreateRequest);

    // 将 userUpdateRequest 中的属性赋值到 user 对象中
    void updateEntity(UserUpdateRequest userUpdateRequest, @MappingTarget User user);

}
