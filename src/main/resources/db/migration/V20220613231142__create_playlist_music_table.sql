create table playlist_music
(
    playlist_id varchar(32) not null comment '歌单ID',
    music_id    varchar(32) not null comment '音乐ID',
    constraint c_playlist_id foreign key (playlist_id) references playlist (id),
    constraint c_music_id foreign key (music_id) references music (id)
) engine = InnoDB
  charset = utf8mb4
  collate = utf8mb4_bin comment '歌单音乐关联表';