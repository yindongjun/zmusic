package com.example.zmusic.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class MusicCreateRequest {

  @NotBlank(message = "音乐名称不能为空")
  @Size(max = 64, message = "音乐名称最大长度为 64")
  private String name;

  @Size(max = 255, message = "音乐简介最大长度为 255")
  private String description;
}
