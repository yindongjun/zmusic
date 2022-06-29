package com.example.zmusic.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.example.zmusic.dto.PlaylistDto;
import com.example.zmusic.dto.PlaylistSearchFilter;
import com.example.zmusic.entity.Playlist;
import com.example.zmusic.enums.PlaylistStatus;
import com.example.zmusic.exception.BizException;
import com.example.zmusic.exception.ExceptionType;
import com.example.zmusic.mapper.MapperInterface;
import com.example.zmusic.mapper.PlaylistMapper;
import com.example.zmusic.repository.PlaylistRepository;
import com.example.zmusic.repository.specs.PlaylistSpecification;
import com.example.zmusic.repository.specs.SearchCriteria;
import com.example.zmusic.repository.specs.SearchOperation;
import com.example.zmusic.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaylistServiceImpl extends SimpleGeneralServiceImpl<Playlist, PlaylistDto>
    implements PlaylistService {

  private final PlaylistMapper playlistMapper;

  private final PlaylistRepository playlistRepository;

  @Override
  public Page<PlaylistDto> search(PlaylistSearchFilter filter) {
    Pageable pageable = filter.toPageable();

    // Build Specification
    PlaylistSpecification specification = new PlaylistSpecification();
    if (StrUtil.isNotBlank(filter.getName())) {
      specification.add(new SearchCriteria("name", filter.getName(), SearchOperation.MATCH));
    }

    if (ObjectUtil.isNotNull(filter.getStatus())) {
      specification.add(
          new SearchCriteria(
              "status", PlaylistStatus.valueOf(filter.getStatus()), SearchOperation.EQUAL));
    }

    return playlistRepository.findAll(specification, pageable).map(playlistMapper::toDto);
  }

  @Override
  @Transactional
  public void publish(String id) {
    Playlist playlist = getEntity(id);
    playlist.setStatus(PlaylistStatus.PUBLISHED);
    playlistRepository.save(playlist);
  }

  @Override
  public void close(String id) {
    Playlist playlist = getEntity(id);
    playlist.setStatus(PlaylistStatus.CLOSED);
    playlistRepository.save(playlist);
  }

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
}
