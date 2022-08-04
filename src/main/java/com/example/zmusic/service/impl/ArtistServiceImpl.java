package com.example.zmusic.service.impl;

import cn.hutool.core.util.StrUtil;
import com.example.zmusic.dto.ArtistDto;
import com.example.zmusic.dto.ArtistSearchFilter;
import com.example.zmusic.entity.Artist;
import com.example.zmusic.enums.ArtistStatus;
import com.example.zmusic.exception.BizException;
import com.example.zmusic.exception.ExceptionType;
import com.example.zmusic.mapper.ArtistMapper;
import com.example.zmusic.mapper.MapperInterface;
import com.example.zmusic.repository.ArtistRepository;
import com.example.zmusic.repository.specs.ArtistSpecification;
import com.example.zmusic.repository.specs.SearchCriteria;
import com.example.zmusic.repository.specs.SearchOperation;
import com.example.zmusic.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArtistServiceImpl extends SimpleGeneralServiceImpl<Artist, ArtistDto>
        implements ArtistService {

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
    public Page<ArtistDto> search(ArtistSearchFilter filter) {
        Pageable pageable = filter.toPageable();

        ArtistSpecification specification = new ArtistSpecification();
        if (StrUtil.isNotBlank(filter.getName())) {
            specification.add(new SearchCriteria("name", filter.getName(), SearchOperation.MATCH));
        }

        if (StrUtil.isNotBlank(filter.getStatus())) {
            specification.add(
                    new SearchCriteria(
                            "status", ArtistStatus.valueOf(filter.getStatus()), SearchOperation.EQUAL));
        }

        return artistRepository.findAll(specification, pageable).map(artistMapper::toDto);
    }
}
