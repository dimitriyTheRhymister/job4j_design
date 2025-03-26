create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 10, 50);
insert into products (name, producer, count, price)
VALUES ('product_2', 'producer_2', 15, 100);
insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 20, 150);

begin transaction;

insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 25, 200);

commit transaction;

select * from products;

begin transaction;

delete from products;

drop table products;

rollback transaction;

select * from products;

begin transaction;

insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 30, 300);

savepoint first_savepoint;

delete from products where price = 150;

update products set price = 75 where name = 'product_1';

select * from products;

rollback to first_savepoint;

select * from products;

insert into products (name, producer, count, price) VALUES ('product_6', 'producer_6', 50, 500);

savepoint second_savepoint;

delete from products where price > 150;

rollback to second_savepoint;

select * from products;

delete from products where price > 150;

select * from products;

rollback to first_savepoint;

select * from products;

update products set price = 600 where name = 'product_6';  --UPDATE 0

rollback to second_savepoint;                              --ОШИБКА:  точка сохранения "second_savepoint" не существует

commit transaction;                                        --ROLLBACK

select * from products;