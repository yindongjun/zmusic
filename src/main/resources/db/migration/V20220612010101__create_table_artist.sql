create table artist
(
    id                varchar(32) not null primary key comment '主键',
    name              varchar(64) not null comment '名称',
    remark            varchar(64) null comment '标注',
    cover_id          varchar(32) null comment '艺术家头像',
    status            varchar(32) not null comment '音乐状态, DRAFT: 草稿状态, PUBLISHED: 上架状态, BLOCKED: 封禁状态',
    create_by_user_id varchar(32) null comment '创建人ID',
    update_by_user_id varchar(32) null comment '更新人ID',
    created_time      datetime(6) null comment '创建时间',
    update_time       datetime(6) null comment '更新时间',
    constraint artist_cover_id foreign key (cover_id) references file (id),
    constraint artist_create_by_user_id foreign key (create_by_user_id) references user (id),
    constraint artist_update_by_user_id foreign key (update_by_user_id) references user (id)
) engine = InnoDB
  default character set = utf8mb4
  collate utf8mb4_bin comment '艺术家';