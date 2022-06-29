package com.example.zmusic.mapper;

import com.example.zmusic.dto.UserDto;
import com.example.zmusic.entity.User;
import com.example.zmusic.request.UserCreateRequest;
import com.example.zmusic.request.UserUpdateRequest;
import com.example.zmusic.vo.UserVo;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder(disableBuilder = true),
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper extends MapperInterface<User, UserDto> {

  UserVo toVo(UserDto userDto);

  List<UserDto> toDto(List<User> users);

  List<UserVo> toVo(List<UserDto> userDtos);

  UserDto toDto(UserCreateRequest userCreateRequest);

  UserDto toDto(UserUpdateRequest userUpdateRequest);
}
