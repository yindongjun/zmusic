package com.example.zmusic.request;

import com.example.zmusic.enums.Gender;
import com.example.zmusic.validate.EnumExist;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UserUpdateRequest {

  /** 用户名 */
  @Size(min = 4, max = 64, message = "用户名长度应该在4个字符到64个字符之间")
  private String username;

  /** 用户昵称 */
  @Size(min = 4, max = 64, message = "昵称长度应该在4个字符到64个字符之间")
  private String nickname;

  /** 性别 */
  @EnumExist(enumClass = Gender.class, message = "性别不符合规范")
  private Gender gender;
}
