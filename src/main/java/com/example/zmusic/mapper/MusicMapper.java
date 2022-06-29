package com.example.zmusic.mapper;

import com.example.zmusic.dto.MusicDto;
import com.example.zmusic.entity.Music;
import com.example.zmusic.request.MusicCreateRequest;
import com.example.zmusic.request.MusicUpdateRequest;
import com.example.zmusic.vo.MusicVo;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface MusicMapper {
  Music createEntity(MusicCreateRequest musicCreateRequest);

  void updateEntity(MusicUpdateRequest musicUpdateRequest, @MappingTarget Music music);

  MusicDto toDto(Music music);

  MusicVo toVo(MusicDto musicDto);
}
