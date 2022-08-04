package com.example.zmusic.dto;

import com.example.zmusic.enums.MusicStatus;
import com.example.zmusic.validate.EnumExist;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class MusicSearchFilter extends PageFilter {

    private String name;

    @EnumExist(enumClass = MusicStatus.class, message = "音乐状态不正确")
    private String status;
}
