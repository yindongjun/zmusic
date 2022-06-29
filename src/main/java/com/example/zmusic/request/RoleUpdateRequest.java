package com.example.zmusic.request;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class RoleUpdateRequest {
  @Size(max = 128, message = "角色名称最长128个字符")
  private String name;

  @Size(max = 128, message = "角色标识最长128个字符")
  private String title;
}
