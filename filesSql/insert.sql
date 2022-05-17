insert into roles (name) values('admin');
insert into users (name, roles_id) values ('Oleg', 1);
insert into rules (name) values ('delete\comments\deleteUsers\deleteItems');
insert into roles_rules(rule_id, role_id) values (1, 1);
insert into item (name, user_id) values ('заявка', 1);
insert into comments (name, item_id) values ('Прочитать заявку внимательно', 1);
insert into attach (name, item_id) values ('File', 1);
insert into category (name, item_id) values('сотрудничество', 1);
insert into state (name, item_id) values('в обработке', 1);
