package com.example.zmusic.mapper;

import com.example.zmusic.dto.ArtistDto;
import com.example.zmusic.entity.Artist;
import com.example.zmusic.request.ArtistCreateRequest;
import com.example.zmusic.request.ArtistUpdateRequest;
import com.example.zmusic.vo.ArtistVo;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        builder = @Builder(disableBuilder = true),
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ArtistMapper extends MapperInterface<Artist, ArtistDto> {

    @Mapping(source = "coverId", target = "cover.id")
    ArtistDto toDto(ArtistCreateRequest artistCreateRequest);

    @Mapping(source = "coverId", target = "cover.id")
    ArtistDto toDto(ArtistUpdateRequest artistUpdateRequest);

    ArtistVo toVo(ArtistDto artistDto);
}
