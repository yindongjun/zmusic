package com.example.zmusic.dto;

import com.example.zmusic.enums.FileType;
import com.example.zmusic.enums.Storage;
import lombok.Data;

@Data
public class StorageDto {
  private String name;

  private Long size;

  private String fileKey;

  private String ext;

  private String url;

  private FileType type;

  private Storage storage;
}
