package com.example.zmusic.mapper;

import com.example.zmusic.dto.LoginDto;
import com.example.zmusic.vo.TokenVo;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TokenMapper {

    TokenVo toVo(LoginDto loginDto);
}
