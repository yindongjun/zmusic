INSERT INTO user (id, username, nickname, password, gender, locked, enabled, last_login_ip, last_login_time,
                  created_time, update_time)
VALUES ('1', 'admin', 'admin', '$2a$10$.XE9judePPAUQLgg6aDs4euMmbW.WkFm9S75cQeNdF6ju/n2OyqHC',
        'MALE', 0, 1, '127.0.0.1', '2022-05-22 22:17:04.969874', '2022-05-18 22:44:13.112018',
        '2022-05-22 22:17:04.990931');

INSERT INTO role(id, name, title, created_time, update_time)
VALUES ('1', 'ROLE_USER', '普通用户', '2022-05-22 22:17:04.990931', '2022-05-22 22:17:04.990931');

INSERT INTO role(id, name, title, created_time, update_time)
VALUES ('2', 'ROLE_ADMIN', '超级管理员', '2022-05-22 22:17:04.990931', '2022-05-22 22:17:04.990931');

INSERT INTO user_role(user_id, role_id)
values ('1', '1');

INSERT INTO user_role(user_id, role_id)
values ('1', '2');