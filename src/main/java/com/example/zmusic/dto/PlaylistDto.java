package com.example.zmusic.dto;

import com.example.zmusic.entity.File;
import com.example.zmusic.entity.Music;
import com.example.zmusic.enums.PlaylistStatus;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PlaylistDto extends BaseDto {

  private String name;

  private String description;

  private File cover;

  private PlaylistStatus status = PlaylistStatus.DRAFT;

  private List<Music> musicList = new ArrayList<>();

  private UserDto createUser;

  private UserDto updateUser;
}
