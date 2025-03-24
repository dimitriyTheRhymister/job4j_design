create table students
(
    id   serial primary key,
    namee varchar(50)
);

insert into students (namee)
values ('Иван Иванов');
insert into students (namee)
values ('Петр Петров');
insert into students (namee)
values ('Николай Николаев');
insert into students (namee)
values ('Стнпан Степанов');
update students
set namee = 'Степан Степанов'
where id = 4;
insert into students (namee)
values ('Андрей Андреев');
insert into students (namee)
values ('Сергей Сергеев');
insert into students (namee)
values ('Антон Антонов');

create table authors
(
    id   serial primary key,
    namee varchar(50)
);

insert into authors (namee)
values ('Александр Пушкин');
insert into authors (namee)
values ('Николай Гоголь');
insert into authors (namee)
values ('Лев Толстой');
insert into authors (namee)
values ('Фёдор Достоевский');

create table books
(
    id serial primary key,
    namee varchar(200),
    author_id integer references authors (id)
);

insert into books (namee, author_id)
values ('Евгений Онегин', 1);
insert into books (namee, author_id)
values ('Капитанская дочка', 1);
insert into books (namee, author_id)
values ('Дубровский', 1);
insert into books (namee, author_id)
values ('Мертвые души', 2);
insert into books (namee, author_id)
values ('Вий', 2);
insert into books (namee, author_id)
values ('Библия', null);
insert into books (namee, author_id)
values ('Война и мир', 3);
insert into books (namee, author_id)
values ('Братья Карамазовы', 4);
insert into books (namee, author_id)
values ('Метель', 1);
insert into books (namee, author_id)
values ('Вечера на хуторе близ Диканьки', 2);

create table orders
(
    id serial primary key,
    active boolean default true,
    book_id integer references books (id),
    student_id integer references students (id)
);

insert into orders (book_id, student_id)
values (1, 1);
insert into orders (book_id, student_id)
values (1, 2);
insert into orders (book_id, student_id)
values (1, 3);
insert into orders (book_id, student_id, active)
values (2, 3, false);
insert into orders (book_id, student_id)
values (2, 1);
insert into orders (book_id, student_id)
values (2, 2);
insert into orders (book_id, student_id)
values (3, 1);
insert into orders (book_id, student_id)
values (4, 1);
insert into orders (book_id, student_id)
values (5, 4);
insert into orders (book_id, student_id, active)
values (4, 4, false);
insert into orders (book_id, student_id)
values (5, 2);
insert into orders (book_id, student_id, active)
values (6, 2, false);
insert into orders (book_id, student_id)
values (7, 1);
insert into orders (book_id, student_id, active)
values (8, 2, false);
insert into orders (book_id, student_id, active)
values (1, 5, false);
insert into orders (book_id, student_id, active)
values (2, 6, false);
insert into orders (book_id, student_id, active)
values (5, 7, false);

select
    b.namee as book_name,
    a.namee as author_name,
    count(DISTINCT o.student_id) as student_count,
    count(case when o.active then 1 end) as active_orders_count,
    count(o.id) as total_orders_count,
    string_agg(s.namee, ', ') as students_names
from
    books b
join
    orders o ON b.id = o.book_id
join
    authors a ON b.author_id = a.id
join
    students s ON o.student_id = s.id
group by
    b.id, b.namee, a.namee
having
    count(DISTINCT o.student_id) >= 2;

create view books_that_are_read_by_2_or_more_students_with_detailed_information
as
select
    b.namee as book_name,
    a.namee as author_name,
    count(DISTINCT o.student_id) as student_count,
    count(case when o.active then 1 end) as active_orders_count,
    count(o.id) as total_orders_count,
    string_agg(s.namee, ', ') as students_names
from
    books b
join
    orders o ON b.id = o.book_id
join
    authors a ON b.author_id = a.id
join
    students s ON o.student_id = s.id
group by
    b.id, b.namee, a.namee
having
    count(DISTINCT o.student_id) >= 2;

select * from books_that_are_read_by_2_or_more_students_with_detailed_information;
