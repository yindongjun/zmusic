package com.example.zmusic.service.impl;

import com.example.zmusic.dto.ArtistDto;
import com.example.zmusic.entity.Artist;
import com.example.zmusic.exception.BizException;
import com.example.zmusic.exception.ExceptionType;
import com.example.zmusic.mapper.ArtistMapper;
import com.example.zmusic.mapper.MapperInterface;
import com.example.zmusic.repository.ArtistRepository;
import com.example.zmusic.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl extends SimpleGeneralServiceImpl<Artist, ArtistDto> implements ArtistService {

    private final ArtistRepository artistRepository;

    private final ArtistMapper artistMapper;

    @Override
    public MapperInterface<Artist, ArtistDto> getMapstructMapper() {
        return artistMapper;
    }

    @Override
    public JpaRepository<Artist, String> getRepository() {
        return artistRepository;
    }

    @Override
    public BizException getNotFoundException() {
        return new BizException(ExceptionType.ARTIST_NOT_FOUND);
    }

    @Override
    public Page<ArtistDto> search(Pageable pageable) {
        return artistRepository.findAll(pageable)
                .map(artistMapper::toDto);
    }
}
