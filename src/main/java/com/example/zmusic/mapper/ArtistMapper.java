package com.example.zmusic.mapper;

import com.example.zmusic.dto.ArtistDto;
import com.example.zmusic.entity.Artist;
import com.example.zmusic.request.ArtistCreateRequest;
import com.example.zmusic.request.ArtistUpdateRequest;
import com.example.zmusic.vo.ArtistVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ArtistMapper extends MapperInterface<Artist, ArtistDto> {

    @Mapping(source = "coverId", target = "cover.id")
    ArtistDto toDto(ArtistCreateRequest artistCreateRequest);


    @Mapping(source = "coverId", target = "cover.id")
    ArtistDto toDto(ArtistUpdateRequest artistUpdateRequest);

    ArtistVo toVo(ArtistDto artistDto);
}
