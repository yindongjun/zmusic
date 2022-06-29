package com.example.zmusic.mapper;

import com.example.zmusic.dto.BaseDto;
import com.example.zmusic.entity.AbstractId;
import org.mapstruct.MappingTarget;

public interface MapperInterface<Entity extends AbstractId, Dto extends BaseDto> {
  Entity toEntity(Dto dto);

  void updateEntity(Dto dto, @MappingTarget Entity entity);

  Dto toDto(Entity entity);
}
