package com.example.zmusic.service;

import com.example.zmusic.dto.PlaylistDto;
import com.example.zmusic.dto.PlaylistSearchFilter;
import com.example.zmusic.entity.Playlist;
import org.springframework.data.domain.Page;

public interface PlaylistService extends GeneralService<Playlist, PlaylistDto> {
  Page<PlaylistDto> search(PlaylistSearchFilter filter);

  void publish(String id);

  void close(String id);
}
