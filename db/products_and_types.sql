create table types(
    id serial primary key,
    p_name varchar(255)
);

create table products(
    id serial primary key,
    t_name varchar(255),
    expired_date varchar(255),
    price int,
    type_id int references types(id)
);

insert into types(t_name) values ('СЫР'), ('МОЛОКО'), ('ХДЕБ'), ('МЯСО'), ('РЫБА'), ('ОВОЩИ'), ('ФРУКТЫ'), ('КОНФЕТЫ'), ('КРУПЫ'));
insert into products(p_name, expired_date, price, type_id) values ('СЫР_1', '2023-11-13', 500, 1), ('СЫР_2', '2023-10-05', 1000, 1), ('СЫР_3', '2023-12-23', 300, 1), ('МОЛОКО_1', '2023-11-13', 500, 2), ('МОЛОКО_2', '2023-10-05', 1000, 2), ('МОЛОКО_3', '2023-12-23', 300, 2), ('ХДЕБ_1', '2023-11-13', 500, 3), ('ХДЕБ_2', '2023-10-05', 1000, 3), ('ХДЕБ_31', '2023-12-23', 300, 3), ('МЯСО_1', '2023-11-13', 500, 4), ('МЯСО_2', '2023-10-05', 1000, 4), ('МЯСО_3', '2023-12-23', 300, 4), ('РЫБА_1', '2023-11-13', 500, 5), ('РЫБА_2', '2023-10-05', 1000, 5), ('РЫБА_3', '2023-12-23', 300, 5), ('ОВОЩИ_1', '2023-11-13', 500, 6), ('ОВОЩИ_2', '2023-10-05', 1000, 6), ('ОВОЩИ_3', '2023-12-23', 300, 6), ('ФРУКТЫ_1', '2023-11-13', 500, 7), ('ФРУКТЫ_2', '2023-10-05', 1000, 7), ('ФРУКТЫ_3', '2023-12-23', 300, 7), ('КОНФЕТЫ_1', '2023-11-13', 500, 8), ('КОНФЕТЫ_2', '2023-10-05', 1000, 8), ('КОНФЕТЫ_3', '2023-12-23', 300, 8), ('КРУПЫ_1', '2023-11-13', 500, 9), ('КРУПЫ_2', '2023-10-05', 1000, 9), ('КРУПЫ_3', '2023-12-23', 300, 9);
insert into products(p_name, expired_date, price, type_id) values ('КОНФЕТЫ_4', '2023-05-13', 400, 8), ('КРУПЫ_4', '2023-10-23', 350, 9);
insert into products(p_name, expired_date, price, type_id) values ('КОНФЕТЫ_5', '2023-06-15', 400, 8);
insert into products(expired_date_2) values ('2023-11-13'), ('2023-10-05'), ('2023-12-23');
insert into products(expired_date_2) values ('2023-11-13'), ('2023-10-05'), ('2023-12-23'), ('2023-11-13'), ('2023-10-05'), ('2023-12-23'), ('2023-11-13'), ('2023-10-05'), ('2023-12-23'), ('2023-11-13'), ('2023-10-05'), ('2023-12-23'), ('2023-11-13'), ('2023-10-05'), ('2023-12-23');

update products set p_name = 'МЯСО_2_мороженое' where name = 'МЯСО_2';
update products set p_name = 'РЫБА_1_мороженое' where name = 'РЫБА_1';
update products set p_name = 'мороженое_РЫБА_1' where name = 'РЫБА_1_мороженое';
update products set p_name = 'ОВОЩИ_2_мороженое' where name = 'ОВОЩИ_2';
update products set p_name = 'ФРУКТЫ_3_мороженое' where name = 'ФРУКТЫ_3';

--1. Написать запрос получение всех продуктов с типом "СЫР"
select p.p_name, p.expired_date, p.price, p.type_id
from products as p
join types as t
on t.id = type_id and t.t_name = 'СЫР';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select * from products
where products.p_name like '%мороженое%';

--3. Написать запрос, который выводит все продукты, срок годности которых уже истек
select * from products
where expired_date < '2023-11-14';

--4. Написать запрос, который выводит самый дорогой продукт. Запрос должен быть универсальный и находить все продукты с максимальной ценой.
select * from products
where products.price = (select max(products.price) from products);

--5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
select t.t_name, count(p.id) as amount
from types as t
join products as p
on t.id = type_id
group by t.t_name
order by amount desc;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select p.p_name, p.expired_date, p.price, p.type_id
from products as p
join types as t
on t.id = type_id
where t.t_name in ('СЫР', 'МОЛОКО');

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 4 штук.
select t.t_name, count(p.type_id)
from types as t
join products as p
on t.id = type_id
group by t.t_name
having count(p.type_id) > 3;

--8. Вывести все продукты и их тип.
select p.p_name, p.expired_date, p.price, p.type_id, t.t_name
from products as p
join types as t
on t.id = type_id;

--3_2. Написать запрос, который выводит все продукты, срок годности которых уже истек
select * from products
where expired_date_2 < current_date;