create table playlist
(
    id                varchar(32)   not null primary key comment '主键',
    name              varchar(64)   not null comment '歌单名字',
    description       varchar(1024) null comment '歌单简介',
    cover_id          varchar(32)   null comment '歌单封面',
    status            varchar(32)   not null default 'DRAFT' comment '歌单状态, DRAFT: 草稿状态, PUBLISHED: 上架状态, CLOSED: 下架状态',
    create_by_user_id varchar(32)   null comment '创建人ID',
    update_by_user_id varchar(32)   null comment '更新人ID',
    created_time      datetime(6)   null comment '创建时间',
    update_time       datetime(6)   null comment '更新时间'
) engine = InnoDB
  charset = utf8mb4
  collate = utf8mb4_bin comment '歌单表';