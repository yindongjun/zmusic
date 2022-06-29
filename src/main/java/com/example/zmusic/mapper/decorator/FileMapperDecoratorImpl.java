package com.example.zmusic.mapper.decorator;

import com.example.zmusic.dto.FileDto;
import com.example.zmusic.mapper.FileMapper;
import com.example.zmusic.service.StorageService;
import com.example.zmusic.vo.FileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class FileMapperDecoratorImpl implements FileMapper {

  @Autowired
  @Qualifier("delegate")
  private FileMapper delegateFileMapper;

  @Autowired private StorageService storageService;

  @Override
  public FileVo toVo(FileDto fileDto) {
    FileVo fileVo = delegateFileMapper.toVo(fileDto);
    // set url
    String fileFullUrl = storageService.getFullUrl(fileDto);
    fileVo.setUrl(fileFullUrl);
    return fileVo;
  }
}
