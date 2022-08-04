package com.example.zmusic.mapper;

import com.example.zmusic.dto.PlaylistDto;
import com.example.zmusic.entity.Playlist;
import com.example.zmusic.request.PlaylistCreateRequest;
import com.example.zmusic.request.PlaylistUpdateRequest;
import com.example.zmusic.vo.PlaylistVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = {UserMapper.class, FileMapper.class, MusicMapper.class},
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PlaylistMapper extends MapperInterface<Playlist, PlaylistDto> {

    PlaylistVo toVo(PlaylistDto playlistDto);

    @Mapping(source = "coverId", target = "cover.id")
    PlaylistDto toDto(PlaylistCreateRequest playlistCreateRequest);

    @Mapping(source = "coverId", target = "cover.id")
    PlaylistDto toDto(PlaylistUpdateRequest playlistUpdateRequest);
}
