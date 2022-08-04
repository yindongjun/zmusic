package com.example.zmusic.service;

import com.example.zmusic.dto.FileDto;
import com.example.zmusic.dto.FileSearchFilter;
import com.example.zmusic.entity.File;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface FileService extends GeneralService<File, FileDto> {

    Page<FileDto> search(FileSearchFilter filter);

    FileDto create(MultipartFile file);

    FileDto update(String id, MultipartFile newFile);

    FileDto getByFileKey(String key);

    String getFullUrl(String id);
}
