package com.example.zmusic.entity;

import com.example.zmusic.enums.PlaylistStatus;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Playlist extends TraceableEntity {
  /** 歌单名字 */
  private String name;

  /** 歌单简介 */
  private String description;

  /** 歌单封面 */
  @OneToOne
  @JoinColumn(name = "cover_id", referencedColumnName = "id")
  private File cover;

  /** 歌单状态, DRAFT: 草稿状态, PUBLISHED: 上架状态, CLOSED: 下架状态 */
  @Enumerated(EnumType.STRING)
  private PlaylistStatus status = PlaylistStatus.DRAFT;

  @ManyToMany
  @JoinTable(
      name = "playlist_music",
      joinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "music_id", referencedColumnName = "id"))
  private List<Music> musicList = new ArrayList<>();
}
