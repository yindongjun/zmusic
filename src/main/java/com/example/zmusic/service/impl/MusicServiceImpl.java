package com.example.zmusic.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.example.zmusic.dto.MusicDto;
import com.example.zmusic.dto.MusicSearchFilter;
import com.example.zmusic.entity.Music;
import com.example.zmusic.enums.MusicStatus;
import com.example.zmusic.exception.BizException;
import com.example.zmusic.exception.ExceptionType;
import com.example.zmusic.mapper.MusicMapper;
import com.example.zmusic.repository.MusicRepository;
import com.example.zmusic.repository.specs.MusicSpecification;
import com.example.zmusic.repository.specs.SearchCriteria;
import com.example.zmusic.repository.specs.SearchOperation;
import com.example.zmusic.request.MusicCreateRequest;
import com.example.zmusic.request.MusicUpdateRequest;
import com.example.zmusic.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicService {

    private final MusicMapper musicMapper;

    private final MusicRepository musicRepository;

    private Music getEntityById(String id) {
        return musicRepository
                .findById(id)
                .orElseThrow(() -> new BizException(ExceptionType.NOT_FOUND));
    }

    @Override
    public MusicDto create(MusicCreateRequest musicCreateRequest) {
        Music music = musicMapper.createEntity(musicCreateRequest);
        preInsert(music);
        Music savedMusic = musicRepository.save(music);
        return musicMapper.toDto(savedMusic);
    }

    private void preInsert(Music music) {
        music.setStatus(MusicStatus.DRAFT);
    }

    @Override
    public MusicDto update(String id, MusicUpdateRequest musicUpdateRequest) {
        Music music = this.getEntityById(id);
        musicMapper.updateEntity(musicUpdateRequest, music);
        Music musicUpdated = musicRepository.save(music);
        return musicMapper.toDto(musicUpdated);
    }

    @Override
    public void delete(String id) {
        musicRepository.deleteById(id);
    }

    @Override
    public MusicDto get(String id) {
        return musicMapper.toDto(this.getEntityById(id));
    }

    @Override
    public Page<MusicDto> search(MusicSearchFilter filter) {
        Pageable pageable = filter.toPageable();

        MusicSpecification specification = new MusicSpecification();

        if (StrUtil.isNotEmpty(filter.getName())) {
            specification.add(new SearchCriteria("name", filter.getName(), SearchOperation.MATCH));
        }

        if (ObjectUtil.isNotNull(filter.getStatus())) {
            specification.add(
                    new SearchCriteria(
                            "status", MusicStatus.valueOf(filter.getStatus()), SearchOperation.EQUAL));
        }

        return musicRepository.findAll(specification, pageable).map(musicMapper::toDto);
    }

    @Override
    public void publish(String id) {
        Music music = getEntityById(id);
        music.setStatus(MusicStatus.PUBLISHED);
        musicRepository.save(music);
    }

    @Override
    public void close(String id) {
        Music music = getEntityById(id);
        music.setStatus(MusicStatus.CLOSED);
        musicRepository.save(music);
    }
}
