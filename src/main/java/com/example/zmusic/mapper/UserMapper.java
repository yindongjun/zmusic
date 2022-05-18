package com.example.zmusic.mapper;

import com.example.zmusic.dto.UserCreateDto;
import com.example.zmusic.dto.UserDto;
import com.example.zmusic.entity.User;
import com.example.zmusic.vo.UserVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserDto toDto(User user);

    UserVo toVo(UserDto userDto);

    List<UserDto> toDto(List<User> users);

    List<UserVo> toVo(List<UserDto> userDtos);

    User toDo(UserCreateDto userCreateDto);
}
