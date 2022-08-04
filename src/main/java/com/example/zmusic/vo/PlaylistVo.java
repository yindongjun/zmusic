package com.example.zmusic.vo;

import com.example.zmusic.enums.PlaylistStatus;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PlaylistVo extends BaseVo {

    private String name;

    private String description;

    private FileVo cover;

    private PlaylistStatus status = PlaylistStatus.DRAFT;

    private List<MusicVo> musicList = new ArrayList<>();

    private UserVo createUser;

    private UserVo updateUser;
}
