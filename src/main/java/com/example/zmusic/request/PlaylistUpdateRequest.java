package com.example.zmusic.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PlaylistUpdateRequest {
  @NotBlank(message = "歌单名不能为空")
  @Size(max = 64, message = "歌单名称最大长度为64个字符")
  private String name;

  @Size(max = 1000, message = "歌单简介最大长度为1000个字符")
  private String description;

  @NotBlank(message = "歌单封面不能为空")
  private String coverId;
}
