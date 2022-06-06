create table music
(
    id           varchar(32)  not null primary key comment '音乐ID',
    name         varchar(64)  not null comment '音乐名称',
    status       varchar(32)  default 'DRAFT' not null comment '音乐状态, 默认为草稿(draft)状态, DRAFT: 草稿状态、PUBLISHED: 已上架、CLOSED: 已下架',
    description  varchar(255) null comment '音乐简介',
    created_time datetime(6)  null comment '创建时间',
    update_time  datetime(6)  null comment '更新时间'
) engine = InnoDB
  default charset = utf8mb4
  collate = utf8mb4_bin comment '音乐表';