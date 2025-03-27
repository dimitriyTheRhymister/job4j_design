Server [localhost]:
Database [postgres]:
Port [5432]:
Username [postgres]: postgres
Пароль пользователя postgres:
psql (16.0)
ПРЕДУПРЕЖДЕНИЕ: Кодовая страница консоли (866) отличается от основной
                страницы Windows (1251).
                8-битовые (русские) символы могут отображаться некорректно.
                Подробнее об этом смотрите документацию psql, раздел
                "Notes for Windows users".
Введите "help", чтобы получить справку.

postgres=# \c db
Вы подключены к базе данных "db" как пользователь "postgres".
db=# create table products
db-# (
db(#     id    serial primary key,
db(#     name  varchar(50),
db(#     count integer default 0,
db(#     price integer
db(# );
CREATE TABLE
db=# insert into products (name, count, price)
db-# VALUES ('product_1', 1, 5);
INSERT 0 1
db=# insert into products (name, count, price)
db-# VALUES ('product_2', 2, 10);
INSERT 0 1
db=# insert into products (name, count, price)
db-# VALUES ('product_3', 3, 15);
INSERT 0 1
db=# insert into products (name, count, price)
db-# VALUES ('product_4', 4, 20);
INSERT 0 1
db=# insert into products (name, count, price)
db-# VALUES ('product_5', 5, 25);
INSERT 0 1
db=# insert into products (name, count, price)
db-# VALUES ('product_6', 6, 30);
INSERT 0 1
db=# insert into products (name, count, price)
db-# VALUES ('product_7', 7, 35);
INSERT 0 1
db=# insert into products (name, count, price)
db-# VALUES ('product_8', 8, 40);
INSERT 0 1
db=# insert into products (name, count, price)
db-# VALUES ('product_9', 9, 45);
INSERT 0 1
db=# insert into products (name, count, price)
db-# VALUES ('product_10', 10, 50);
INSERT 0 1
db=# insert into products (name, count, price)
db-# VALUES ('product_11', 11, 55);
INSERT 0 1
db=# insert into products (name, count, price)
db-# VALUES ('product_12', 12, 60);
INSERT 0 1
db=# insert into products (name, count, price)
db-# VALUES ('product_13', 13, 65);
INSERT 0 1
db=# insert into products (name, count, price)
db-# VALUES ('product_14', 14, 70);
INSERT 0 1
db=# insert into products (name, count, price)
db-# VALUES ('product_15', 15, 75);
INSERT 0 1
db=# insert into products (name, count, price)
db-# VALUES ('product_16', 16, 80);
INSERT 0 1
db=# insert into products (name, count, price)
db-# VALUES ('product_17', 17, 85);
INSERT 0 1
db=# insert into products (name, count, price)
db-# VALUES ('product_18', 18, 90);
INSERT 0 1
db=# insert into products (name, count, price)
db-# VALUES ('product_19', 19, 95);
INSERT 0 1
db=# insert into products (name, count, price)
db-# VALUES ('product_20', 20, 100);
INSERT 0 1
db=# BEGIN;
BEGIN
db=*#
db=*# BEGIN;
ПРЕДУПРЕЖДЕНИЕ:  транзакция уже выполняется
BEGIN
db=*#
db=*# DECLARE cursor_products SCROLL for select * from products;
ОШИБКА:  ошибка синтаксиса (примерное положение: "for")
СТРОКА 1: DECLARE cursor_products SCROLL for select * from products;
                                         ^
db=!# DECLARE cursor_products SCROLL cursor for select * from products;
ОШИБКА:  текущая транзакция прервана, команды до конца блока транзакции игнорируются
db=!# rollback;
ROLLBACK
db=# BEGIN;
BEGIN
db=*# DECLARE cursor_products SCROLL cursor for select * from products;
DECLARE CURSOR
db=*# MOVE FORWARD 19 FROM cursor_products;
MOVE 19
db=*# FETCH NEXT FROM cursor_products;
 id |    name    | count | price
----+------------+-------+-------
 20 | product_20 |    20 |   100
(1 ёЄЁюър)


db=*# MOVE BACKWARD 5 FROM cursor_products;
MOVE 5
db=*# FETCH PRIOR FROM cursor_products;
 id |    name    | count | price
----+------------+-------+-------
 14 | product_14 |    14 |    70
(1 ёЄЁюър)


db=*# FETCH NEXT FROM cursor_products;
 id |    name    | count | price
----+------------+-------+-------
 15 | product_15 |    15 |    75
(1 ёЄЁюър)


db=*# MOVE BACKWARD 8 FROM cursor_products;
MOVE 8
db=*# FETCH NEXT FROM cursor_products;
 id |   name    | count | price
----+-----------+-------+-------
  8 | product_8 |     8 |    40
(1 ёЄЁюър)


db=*# FETCH PRIOR FROM cursor_products;
 id |   name    | count | price
----+-----------+-------+-------
  7 | product_7 |     7 |    35
(1 ёЄЁюър)


db=*# MOVE BACKWARD 4 FROM cursor_products;
MOVE 4
db=*# FETCH NEXT FROM cursor_products;
 id |   name    | count | price
----+-----------+-------+-------
  4 | product_4 |     4 |    20
(1 ёЄЁюър)


db=*# MOVE BACKWARD 2 FROM cursor_products;
MOVE 2
db=*# FETCH NEXT FROM cursor_products;
 id |   name    | count | price
----+-----------+-------+-------
  3 | product_3 |     3 |    15
(1 ёЄЁюър)


db=*# FETCH PRIOR FROM cursor_products;
 id |   name    | count | price
----+-----------+-------+-------
  2 | product_2 |     2 |    10
(1 ёЄЁюър)


db=*# FETCH NEXT FROM cursor_products;
 id |   name    | count | price
----+-----------+-------+-------
  3 | product_3 |     3 |    15
(1 ёЄЁюър)


db=*# FETCH NEXT FROM cursor_products;
 id |   name    | count | price
----+-----------+-------+-------
  4 | product_4 |     4 |    20
(1 ёЄЁюър)


db=*# MOVE BACKWARD 2 FROM cursor_products;
MOVE 2
db=*# FETCH PRIOR FROM cursor_products;
 id |   name    | count | price
----+-----------+-------+-------
  1 | product_1 |     1 |     5
(1 ёЄЁюър)


db=*# CLOSE cursor_products;
CLOSE CURSOR
db=*# COMMIT;
COMMIT
db=#