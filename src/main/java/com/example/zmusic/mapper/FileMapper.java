package com.example.zmusic.mapper;

import com.example.zmusic.dto.FileDto;
import com.example.zmusic.dto.StorageDto;
import com.example.zmusic.entity.File;
import com.example.zmusic.vo.FileVo;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface FileMapper {

    FileVo toVo(FileDto fileDto);

    FileDto toDto(File file);

    File createEntity(FileDto fileDto);

    File createEntity(StorageDto storageDto);


    void updateEntity(FileDto fileDto, @MappingTarget File file);

    void updateEntity(StorageDto storageDto, @MappingTarget File file);

}
