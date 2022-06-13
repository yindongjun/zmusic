create table file
(
    id           varchar(32)                     not null primary key comment '文件ID',
    name         varchar(64)                     not null comment '文件名',
    file_key     varchar(64)                     not null unique comment '文件key',
    url          varchar(1024)                   null comment '本地文件存放路径, 云存储文件路径不设置',
    ext          varchar(32)                     not null comment '文件后缀名',
    size         bigint      default 0           not null comment '文件大小: 单位 byte',
    type         varchar(32) default 'OTHER'     not null comment '文件类型: AUDIO-音频，IMAGE-图片，VIDEO-视频，OTHER-其他',
    storage      varchar(16) default 'LOCAL'     not null comment '存储供应商: LOCAL-本地文件存储, COS-腾讯云存储, OSS-阿里云存储',
    status       varchar(32) default 'UPLOADING' not null comment '文件状态: UPLOADING-上传中，UPLOADED-已上传，CANCEL-已取消',
    created_time datetime(6)                     null comment '创建时间',
    update_time  datetime(6)                     null comment '更新时间'
) engine = InnoDB
  default charset = utf8mb4
  collate = utf8mb4_bin comment '文件表';