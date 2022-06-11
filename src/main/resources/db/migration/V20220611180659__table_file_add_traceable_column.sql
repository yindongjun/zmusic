alter table file
    add column create_by_user_id varchar(32) null comment '创建用户ID',
    add constraint c_create_by_user_id foreign key (create_by_user_id) references user (id);

alter table file
    add column update_by_user_id varchar(32) null comment '更新用户ID',
    add constraint c_update_by_user_id foreign key (update_by_user_id) references user (id);