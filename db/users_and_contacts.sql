create table contacts(
    id serial primary key,
    telefon varchar(255),
    email varchar(255)
);

create table users(
    id serial primary key,
    user_name varchar(255),
    contacts_id int references contacts(id) unique
);

insert into contacts(telefon, email) values ('8-354-221-111', '123456@ya.ru');
insert into contacts(telefon, email) values ('8-354-221-112', '123457@ya.ru');
insert into contacts(telefon, email) values ('8-354-221-113', '123458@ya.ru');

insert into users(user_name, contacts_id) values ('Ivan', 1);
insert into users(user_name, contacts_id) values ('Stepan', 2);
insert into users(user_name, contacts_id) values ('Mihail', 3);
insert into users(user_name) values ('Vasya');
insert into users(user_name) values ('Anya');

select * from users as u
inner join contacts as � on u.contacts_id = �.id;

select u.user_name, �.telefon
from users as u join contacts as � on u.contacts_id = �.id;

select u.user_name as ���, �.email as "�������-�����"
from users as u join contacts as � on u.contacts_id = �.id;

select u.user_name as "��� ������������", �.telefon �������, �.email �����
from users u join contacts � on u.contacts_id = �.id;