CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

INSERT INTO customers
VALUES (1, '����', '���', 22, '���'),
       (2, '����', '������', 33, '���'),
       (3, '����', '����', 25, '���'),
       (4, '����', '������', 51, '���'),
       (5, '�����', '�����', 43, '���');

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