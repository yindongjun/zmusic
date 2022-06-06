package com.example.zmusic.entity;

import com.example.zmusic.enums.MusicStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Music extends AbstractEntity {
    /**
     * 音乐名称
     */
    private String name;

    /**
     * 音乐状态
     */
    @Enumerated(value = EnumType.STRING)
    private MusicStatus status;

    /**
     * 音乐简介
     */
    private String description;

}
