package com.example.zmusic.service;

import com.example.zmusic.dto.BaseDto;
import com.example.zmusic.entity.AbstractId;
import com.example.zmusic.exception.BizException;
import com.example.zmusic.mapper.MapperInterface;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralService<Entity extends AbstractId, Dto extends BaseDto> {
    MapperInterface<Entity, Dto> getMapstructMapper();

    JpaRepository<Entity, String> getRepository();

    BizException getNotFoundException();

    Entity getEntity(String id);

    Dto get(String id);

    Dto create(Dto creationDto);

    Dto update(String id, Dto updateDto);

    void delete(String id);
}
