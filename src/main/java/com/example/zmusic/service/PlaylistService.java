package com.example.zmusic.service;

import com.example.zmusic.dto.PlaylistDto;
import com.example.zmusic.entity.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaylistService extends GeneralService<Playlist, PlaylistDto> {
    Page<PlaylistDto> search(Pageable pageable);
}
