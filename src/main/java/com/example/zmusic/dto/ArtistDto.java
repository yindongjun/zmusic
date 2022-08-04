package com.example.zmusic.dto;

import com.example.zmusic.entity.File;
import com.example.zmusic.entity.Music;
import com.example.zmusic.enums.ArtistStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ArtistDto extends BaseDto {

    private String name;

    private String remark;

    private List<Music> musicList;

    private ArtistStatus status = ArtistStatus.DRAFT;

    private File cover;
}
