package com.example.zmusic.dto;

import com.example.zmusic.enums.FileStatus;
import com.example.zmusic.enums.FileType;
import com.example.zmusic.validate.EnumExist;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FileSearchFilter extends PageFilter {

    private String name;

    @EnumExist(enumClass = FileType.class, message = "文件类型错误")
    private String type;

    @EnumExist(enumClass = FileStatus.class, message = "文件状态错误")
    private String status;
}
