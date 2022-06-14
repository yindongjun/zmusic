package com.example.zmusic.dto;

import com.example.zmusic.entity.File;
import com.example.zmusic.entity.Music;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PlaylistDto extends BaseDto {

    private String name;

    private String description;

    private File cover;

    private List<Music> musicList = new ArrayList<>();

    private UserDto createUser;

    private UserDto updateUser;
}
