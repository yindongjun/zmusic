package com.example.zmusic.service;

import com.example.zmusic.dto.ArtistDto;
import com.example.zmusic.dto.ArtistSearchFilter;
import com.example.zmusic.entity.Artist;
import org.springframework.data.domain.Page;

public interface ArtistService extends GeneralService<Artist, ArtistDto> {
  Page<ArtistDto> search(ArtistSearchFilter filter);
}
