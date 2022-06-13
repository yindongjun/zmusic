package com.example.zmusic.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = false)
public class ArtistUpdateRequest {

    @Size(min = 1, max = 64, message = "艺术家名称长度在1个字符到64字符之间")
    private String name;

    @Size(max = 64, message = "艺术家标注最大长度为64个字符")
    private String remark;

    private String coverId;
}
