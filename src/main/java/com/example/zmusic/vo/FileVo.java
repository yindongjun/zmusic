package com.example.zmusic.vo;

import com.example.zmusic.enums.FileStatus;
import com.example.zmusic.enums.FileType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FileVo extends BaseVo {
  private String name;

  private Long size;

  private String fileKey;

  private String ext;

  private String url;

  private FileType type;

  private FileStatus status;
}
