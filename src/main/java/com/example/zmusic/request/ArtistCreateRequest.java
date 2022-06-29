package com.example.zmusic.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ArtistCreateRequest {
  @NotBlank(message = "艺术家名称不能为空")
  @Size(min = 1, max = 64, message = "艺术家名称长度在1个字符到64字符之间")
  private String name;

  @Size(max = 64, message = "艺术家标注最大长度为64个字符")
  private String remark;

  @NotBlank(message = "艺术家照片不能为空")
  private String coverId;
}
