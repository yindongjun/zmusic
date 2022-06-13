package com.example.zmusic.vo;

import com.example.zmusic.entity.File;
import com.example.zmusic.entity.Music;
import com.example.zmusic.enums.ArtistStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ArtistVo extends BaseVo {
    private String name;

    private String remark;

    private List<Music> musicList;

    private ArtistStatus status;

    private File cover;
}
