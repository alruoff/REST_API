-- инициализация БД первичными данными для входа admin
insert into user (password, login)
values ('$2a$10$Y2NUZtK9KoEu156krlBubON2G5dstErBSsC0IJyxOAI053jKrDc0u', 'admin');

insert into customer (email, full_name, is_blocked, position)
values ('sa@system.de','Vasiliy Ivanovich', false, 'ROLE_ADMIN');

update user
set customer_id = 1 where user.id = 1;
update customer
set created_at = current_timestamp() where id = 1;
update customer
set updated_at = current_timestamp() where id = 1;

insert into role (name)
values ('ROLE_ADMIN');

insert into role (name)
values ('ROLE_USER');

insert into role (name)
values ('ROLE_MANAGER');

insert into role (name)
values ('ROLE_TECHNOMAN');

insert into user_role (user_id, role_id)
values (1, 1);

insert into hibernate_sequence (next_val)
values (2);