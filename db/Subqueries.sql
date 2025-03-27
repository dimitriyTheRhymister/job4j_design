CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

INSERT INTO customers
VALUES (1, 'Джон', 'Доу', 22, 'США'),
       (2, 'Ганс', 'Грубер', 33, 'Кан'),
       (3, 'Сара', 'Смит', 25, 'Авс'),
       (4, 'Иван', 'Иванов', 51, 'Рос'),
       (5, 'Грета', 'Купер', 43, 'Нид');

select * from customers
where customers.age = (select min(customers.age) from customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO orders
VALUES (1, 20, 2),
       (2, 30, 4),
       (3, 25, 1),
       (4, 50, 2),
       (5, 40, 4);

SELECT *
FROM customers WHERE customers.id NOT IN (SELECT customer_id FROM orders);