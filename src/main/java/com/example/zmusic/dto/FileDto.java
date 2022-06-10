package com.example.zmusic.dto;

import com.example.zmusic.enums.FileStatus;
import com.example.zmusic.enums.FileType;
import com.example.zmusic.enums.Storage;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileDto {
    private String id;

    private String name;

    private Long size;

    private String fileKey;

    private String ext;

    private FileType type;

    private Storage storage;

    private FileStatus status;

    private LocalDateTime createdTime;

    private LocalDateTime updateTime;
}
