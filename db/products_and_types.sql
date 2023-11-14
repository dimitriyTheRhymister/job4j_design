create table types(
    id serial primary key,
    name varchar(255)
);

create table products(
    id serial primary key,
    name varchar(255),
    expired_date varchar(255),
    price float,
    type_id int references types(id)
);

insert into types(name) values ('���'), ('������'), ('����'), ('����'), ('����'), ('�����'), ('������'), ('�������'), ('�����'));
insert into products(name, expired_date, price, type_id) values ('���_1', '2023-11-13', 500, 1), ('���_2', '2023-10-05', 1000, 1), ('���_3', '2023-12-23', 300, 1), ('������_1', '2023-11-13', 500, 2), ('������_2', '2023-10-05', 1000, 2), ('������_3', '2023-12-23', 300, 2), ('����_1', '2023-11-13', 500, 3), ('����_2', '2023-10-05', 1000, 3), ('����_31', '2023-12-23', 300, 3), ('����_1', '2023-11-13', 500, 4), ('����_2', '2023-10-05', 1000, 4), ('����_3', '2023-12-23', 300, 4), ('����_1', '2023-11-13', 500, 5), ('����_2', '2023-10-05', 1000, 5), ('����_3', '2023-12-23', 300, 5), ('�����_1', '2023-11-13', 500, 6), ('�����_2', '2023-10-05', 1000, 6), ('�����_3', '2023-12-23', 300, 6), ('������_1', '2023-11-13', 500, 7), ('������_2', '2023-10-05', 1000, 7), ('������_3', '2023-12-23', 300, 7), ('�������_1', '2023-11-13', 500, 8), ('�������_2', '2023-10-05', 1000, 8), ('�������_3', '2023-12-23', 300, 8), ('�����_1', '2023-11-13', 500, 9), ('�����_2', '2023-10-05', 1000, 9), ('�����_3', '2023-12-23', 300, 9);
insert into products(name, expired_date, price, type_id) values ('�������_4', '2023-05-13', 400, 8), ('�����_4', '2023-10-23', 350, 9);
insert into products(name, expired_date, price, type_id) values ('�������_5', '2023-06-15', 400, 8);

update products set name = '����_2_���������' where name = '����_2';
update products set name = '����_1_���������' where name = '����_1';
update products set name = '���������_����_1' where name = '����_1_���������';
update products set name = '�����_2_���������' where name = '�����_2';
update products set name = '������_3_���������' where name = '������_3';

--1. �������� ������ ��������� ���� ��������� � ����� "���"
select p.name, p.expired_date, p.price, p.type_id
from products as p
join types as t
on t.id = type_id and t.name = '���';

--2. �������� ������ ��������� ���� ���������, � ���� � ����� ���� ����� "���������"
select * from products
where products.name like '%���������%';

--3. �������� ������, ������� ������� ��� ��������, ���� �������� ������� ��� �����
select * from products
where expired_date < '2023-11-14';

--4. �������� ������, ������� ������� ����� ������� �������. ������ ������ ���� ������������� � �������� ��� �������� � ������������ �����.
select * from products
where products.price = (select max(products.price) from products);

--5. �������� ������, ������� ������� ��� ������� ���� ���������� ��������� � ���� �������������. � ���� ���_����, ����������
select t.name, count(p.id)
from types as t
join products as p
on t.id = type_id
group by t.name;

--6. �������� ������ ��������� ���� ��������� � ����� "���" � "������"
select p.name, p.expired_date, p.price, p.type_id
from products as p
join types as t
on t.id = type_id and (t.name = '���' or t.name = '������');

--7. �������� ������, ������� ������� ��� ���������, ������� �������� ������ 4 ����.
select t.name, count(p.type_id)
from types as t
join products as p
on t.id = type_id
group by t.name
having count(p.type_id) > 3;

--8. ������� ��� �������� � �� ���.
select p.name, p.expired_date, p.price, p.type_id, t.name
from products as p
join types as t
on t.id = type_id;

