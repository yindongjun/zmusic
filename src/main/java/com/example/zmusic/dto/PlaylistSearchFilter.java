package com.example.zmusic.dto;

import com.example.zmusic.enums.PlaylistStatus;
import com.example.zmusic.validate.EnumExist;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PlaylistSearchFilter extends PageFilter {

    private String name;

    private String description;

    @EnumExist(enumClass = PlaylistStatus.class, message = "艺术家状态不正确")
    private String status;
}
