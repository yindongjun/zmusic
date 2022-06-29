package com.example.zmusic.dto;

import com.example.zmusic.entity.User;
import com.example.zmusic.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDto extends BaseDto {
  private String username;

  private String nickname;

  private String password;

  private String confirmPassword;

  private Gender gender;

  private List<RoleDto> roles;

  private Boolean locked;

  private Boolean enabled;

  private String lastLoginIp;

  private LocalDateTime lastLoginTime;

  protected User createUser;

  protected User updateUser;
}
