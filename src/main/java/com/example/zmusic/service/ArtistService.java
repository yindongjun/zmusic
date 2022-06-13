package com.example.zmusic.service;

import com.example.zmusic.dto.ArtistDto;
import com.example.zmusic.entity.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArtistService extends GeneralService<Artist, ArtistDto> {
    Page<ArtistDto> search(Pageable pageable);
}
