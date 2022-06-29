package com.example.zmusic.controller;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@ApiModel("修改用户密码")
public class UserUpdatePasswordRequest {
  @NotBlank(message = "密码不能为空")
  @Size(min = 4, max = 64, message = "密码长度在4个字符到64个字符之间")
  private String password;

  @NotBlank(message = "确认密码不能为空")
  @Size(min = 4, max = 64, message = "确认密码长度在4个字符到64个字符之间")
  private String confirmPassword;
}
