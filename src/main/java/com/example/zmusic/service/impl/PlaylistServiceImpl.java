package com.example.zmusic.service.impl;

import com.example.zmusic.dto.PlaylistDto;
import com.example.zmusic.entity.Playlist;
import com.example.zmusic.exception.BizException;
import com.example.zmusic.exception.ExceptionType;
import com.example.zmusic.mapper.MapperInterface;
import com.example.zmusic.mapper.PlaylistMapper;
import com.example.zmusic.repository.PlaylistRepository;
import com.example.zmusic.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl extends SimpleGeneralServiceImpl<Playlist, PlaylistDto> implements PlaylistService {

    private final PlaylistMapper playlistMapper;

    private final PlaylistRepository playlistRepository;

    @Override
    public MapperInterface<Playlist, PlaylistDto> getMapstructMapper() {
        return playlistMapper;
    }

    @Override
    public JpaRepository<Playlist, String> getRepository() {
        return playlistRepository;
    }

    @Override
    public BizException getNotFoundException() {
        return new BizException(ExceptionType.PLAYLIST_NOT_FOUND);
    }

    @Override
    public Page<PlaylistDto> search(Pageable pageable) {
        return playlistRepository.findAll(pageable).map(playlistMapper::toDto);
    }
}
