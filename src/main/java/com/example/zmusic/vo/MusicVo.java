package com.example.zmusic.vo;

import com.example.zmusic.enums.MusicStatus;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MusicVo extends BasicVo {
    private String name;

    private MusicStatus status;

    private String description;
}
