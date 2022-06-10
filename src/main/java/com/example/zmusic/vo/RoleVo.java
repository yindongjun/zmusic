package com.example.zmusic.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleVo extends BasicVo {
    private String name;

    private String title;
}
