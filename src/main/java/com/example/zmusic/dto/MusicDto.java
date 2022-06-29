package com.example.zmusic.dto;

import com.example.zmusic.entity.Artist;
import com.example.zmusic.enums.MusicStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicDto {

  private String id;

  private String name;

  private MusicStatus status;

  private String description;

  private List<Artist> artistList = new ArrayList<>();

  private LocalDateTime createdTime;

  private LocalDateTime updateTime;
}
