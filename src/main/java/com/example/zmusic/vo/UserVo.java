package com.example.zmusic.vo;

import com.example.zmusic.enums.Gender;
import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVo extends BasicVo {
    private String username;

    private String nickname;

    private Gender gender;

    private Boolean locked;

    private Boolean enabled;

    private List<RoleVo> roles;
}
