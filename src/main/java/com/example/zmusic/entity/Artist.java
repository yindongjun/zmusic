package com.example.zmusic.entity;

import com.example.zmusic.enums.ArtistStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Artist extends TraceableEntity {
  private String name;

  private String remark;

  // TODO: 歌手无需展示全部的音乐, 需要进行分页
  @ManyToMany
  @JoinTable(
      name = "artist_music",
      joinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "music_id", referencedColumnName = "id"))
  private List<Music> musics = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  private ArtistStatus status;

  @OneToOne
  @JoinColumn(name = "cover_id", referencedColumnName = "id")
  private File cover;
}
