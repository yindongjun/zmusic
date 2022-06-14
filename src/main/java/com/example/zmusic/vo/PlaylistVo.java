package com.example.zmusic.vo;

import com.example.zmusic.dto.UserDto;
import com.example.zmusic.entity.File;
import com.example.zmusic.entity.Music;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PlaylistVo extends BaseVo {

    private String name;

    private String description;

    private File cover;

    private List<Music> musicList = new ArrayList<>();

    private UserDto createUser;

    private UserDto updateUser;
}
