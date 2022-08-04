package com.example.zmusic.dto;

import com.example.zmusic.enums.ArtistStatus;
import com.example.zmusic.validate.EnumExist;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ArtistSearchFilter extends PageFilter {

    private String name;

    @EnumExist(enumClass = ArtistStatus.class)
    private String status;
}
