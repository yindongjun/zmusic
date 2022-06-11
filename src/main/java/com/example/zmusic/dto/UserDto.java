package com.example.zmusic.dto;

import com.example.zmusic.entity.User;
import com.example.zmusic.enums.Gender;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDto {
    private String id;

    private String username;

    private String nickname;

    private Gender gender;

    private List<RoleDto> roles;

    private Boolean locked;

    private Boolean enabled;

    private String lastLoginIp;

    private LocalDateTime lastLoginTime;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    protected User createUser;

    protected User updateUser;
}
