package com.example.zmusic.entity;

import com.example.zmusic.enums.MusicStatus;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Music extends BaseEntity {

    /**
     * 音乐名称
     */
    private String name;

    /**
     * 音乐状态
     */
    @Enumerated(value = EnumType.STRING)
    private MusicStatus status;

    @ManyToMany
    @JoinTable(
            name = "artist_music",
            joinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "music_id", referencedColumnName = "id"))
    private List<Artist> artistList = new ArrayList<>();

    /**
     * 音乐简介
     */
    private String description;
}
