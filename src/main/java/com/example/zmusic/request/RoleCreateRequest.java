package com.example.zmusic.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RoleCreateRequest {
  @NotNull(message = "角色名不能为空")
  @Size(max = 128, message = "角色名称最长128个字符")
  private String name;

  @NotNull(message = "角色标识不能为空")
  @Size(max = 128, message = "角色标识最长128个字符")
  private String title;
}
