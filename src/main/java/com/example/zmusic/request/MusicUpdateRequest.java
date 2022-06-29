package com.example.zmusic.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicUpdateRequest {
  /** 音乐名称 */
  @NotBlank(message = "音乐名称不能为空")
  @Size(max = 64, message = "音乐名称最大长度为 64")
  private String name;

  /** 音乐简介 */
  @Size(max = 255, message = "音乐简介最大长度为 255")
  private String description;
}
