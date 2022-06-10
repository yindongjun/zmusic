package com.example.zmusic.service;

import com.example.zmusic.dto.FileDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FileDto get(String id);

    Page<FileDto> search(Pageable pageable);

    FileDto create(MultipartFile file);

    FileDto update(String id, MultipartFile newFile);

    void deleteById(String id);

    FileDto getByFileKey(String key);

}
