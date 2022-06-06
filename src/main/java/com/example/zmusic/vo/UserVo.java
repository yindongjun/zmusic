package com.example.zmusic.vo;

import com.example.zmusic.enums.Gender;
import lombok.Data;

import java.util.List;

@Data
public class UserVo {
    private String id;

    private String username;

    private String nickname;

    private Gender gender;

    private Boolean locked;

    private Boolean enabled;

    private List<RoleVo> roles;
}
