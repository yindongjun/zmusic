package com.example.zmusic.entity;

import com.example.zmusic.enums.PlayListStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Playlist extends TraceableEntity {
    /**
     * 歌单名字
     */
    private String name;

    /**
     * 歌单简介
     */
    private String description;

    /**
     * 歌单封面
     */
    @OneToOne
    @JoinColumn(name = "cover_id", referencedColumnName = "id")
    private File cover;

    /**
     * 歌单状态, DRAFT: 草稿状态, PUBLISHED: 上架状态, CLOSED: 下架状态
     */
    @Enumerated(EnumType.STRING)
    private PlayListStatus status = PlayListStatus.DRAFT;

    @ManyToMany
    @JoinTable(name = "playlist_music",
            joinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "music_id", referencedColumnName = "id"))
    private List<Music> musicList = new ArrayList<>();
}

