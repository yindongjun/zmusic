create table user
(
    id              varchar(32) not null primary key comment '用户ID',
    username        varchar(64) not null comment '用户名',
    nickname        varchar(64) null comment '用户昵称',
    password        varchar(64) not null comment '加密后的密码',
    gender          varchar(64) null comment '密码',
    locked          tinyint(1)  not null default 0 comment '是否锁定, 1-是、0-否',
    enabled         tinyint(1)  not null default 1 comment '是否可用, 1-是、0-否',
    last_login_ip   varchar(64) null comment '最后登录IP',
    last_login_time datetime(6) null comment '最后登录时间',
    created_time    datetime(6) not null comment '创建时间',
    update_time     datetime(6) not null comment '更新时间',
    constraint uk_user_username unique (username)
) engine = InnoDB
  default charset = utf8mb4
  collate = utf8mb4_bin comment '用户表';