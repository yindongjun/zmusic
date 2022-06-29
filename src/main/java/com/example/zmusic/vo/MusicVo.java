package com.example.zmusic.vo;

import com.example.zmusic.entity.Artist;
import com.example.zmusic.enums.MusicStatus;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicVo extends BaseVo {
  private String name;

  private MusicStatus status;

  private List<Artist> artistList = new ArrayList<>();

  private String description;
}
