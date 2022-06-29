package com.example.zmusic.service.impl;

import com.example.zmusic.dto.BaseDto;
import com.example.zmusic.entity.AbstractId;
import com.example.zmusic.service.GeneralService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public abstract class SimpleGeneralServiceImpl<Entity extends AbstractId, Dto extends BaseDto>
    implements GeneralService<Entity, Dto> {

  @Override
  public Entity getEntity(String id) {
    JpaRepository<Entity, String> repository = getRepository();
    return repository.findById(id).orElseThrow(this::getNotFoundException);
  }

  @Override
  public Dto get(String id) {
    return getMapstructMapper().toDto(getEntity(id));
  }

  @Override
  @Transactional
  public Dto create(Dto dto) {
    Entity entity = getMapstructMapper().toEntity(dto);
    Entity savedEntity = getRepository().save(entity);
    return getMapstructMapper().toDto(savedEntity);
  }

  @Override
  @Transactional
  public Dto update(String id, Dto dto) {
    Entity entity = getEntity(id);
    getMapstructMapper().updateEntity(dto, entity);
    Entity savedEntity = getRepository().save(entity);
    return getMapstructMapper().toDto(savedEntity);
  }

  @Override
  @Transactional
  public void delete(String id) {
    Entity entity = getEntity(id);
    getRepository().delete(entity);
  }
}
