package com.example.zmusic.dto;

import com.example.zmusic.entity.User;
import com.example.zmusic.enums.FileStatus;
import com.example.zmusic.enums.FileType;
import com.example.zmusic.enums.Storage;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FileDto extends BaseDto {
  private String name;

  private Long size;

  private String fileKey;

  private String ext;

  private String url;

  private FileType type;

  private Storage storage;

  private FileStatus status;

  protected User createUser;

  private User updateUser;
}
