package com.example.zmusic.service;

import com.example.zmusic.dto.MusicDto;
import com.example.zmusic.dto.MusicSearchFilter;
import com.example.zmusic.request.MusicCreateRequest;
import com.example.zmusic.request.MusicUpdateRequest;
import org.springframework.data.domain.Page;

public interface MusicService {

    MusicDto create(MusicCreateRequest musicCreateRequest);

    MusicDto update(String id, MusicUpdateRequest musicUpdateRequest);

    void delete(String id);

    MusicDto get(String id);

    Page<MusicDto> search(MusicSearchFilter filter);

    void publish(String id);

    void close(String id);
}
