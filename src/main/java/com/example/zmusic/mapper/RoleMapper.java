package com.example.zmusic.mapper;

import com.example.zmusic.dto.RoleDto;
import com.example.zmusic.entity.Role;
import com.example.zmusic.request.RoleCreateRequest;
import com.example.zmusic.request.RoleUpdateRequest;
import com.example.zmusic.vo.RoleVo;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        builder = @Builder(disableBuilder = true),
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RoleMapper extends MapperInterface<Role, RoleDto> {

    RoleDto toDto(RoleCreateRequest roleCreateRequest);

    RoleDto toDto(RoleUpdateRequest roleUpdateRequest);

    RoleVo toVo(RoleDto roleDto);

    List<RoleVo> toVo(List<RoleDto> roleList);
}
