create table artist_music
(
    music_id  varchar(32) not null comment '音乐ID',
    artist_id varchar(32) not null comment '艺术家ID',
    constraint artist_music_music_id foreign key (music_id) references music (id),
    constraint artist_music_artist_id foreign key (artist_id) references artist (id)
) engine = InnoDB
  default character set utf8mb4
  collate utf8mb4_bin comment '艺术家和音乐关联表';