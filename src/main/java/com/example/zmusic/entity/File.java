package com.example.zmusic.entity;

import com.example.zmusic.enums.FileStatus;
import com.example.zmusic.enums.FileType;
import com.example.zmusic.enums.Storage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class File extends TraceableEntity {
  private String name;

  private Long size;

  private String fileKey;

  private String ext;

  private String url;

  @Enumerated(EnumType.STRING)
  private FileType type;

  @Enumerated(EnumType.STRING)
  private Storage storage;

  @Enumerated(EnumType.STRING)
  private FileStatus status = FileStatus.UPLOADING;
}
