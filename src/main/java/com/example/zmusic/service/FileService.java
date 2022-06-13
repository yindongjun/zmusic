package com.example.zmusic.service;

import com.example.zmusic.dto.FileDto;
import com.example.zmusic.entity.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface FileService extends GeneralService<File, FileDto> {

    Page<FileDto> search(Pageable pageable);

    FileDto create(MultipartFile file);

    FileDto update(String id, MultipartFile newFile);

    FileDto getByFileKey(String key);
}
