create table departments(
    id serial primary key,
    d_name varchar(255)
);

create table employees(
    id serial primary key,
    e_name varchar(255),
    department_id int references departments(id)
);

create table teens(
    id serial primary key,
    t_name varchar(255),
    gender int
);

insert into departments(d_name) values ('IT');
insert into departments(d_name) values ('sales');
insert into departments(d_name) values ('security');

insert into employees(e_name, department_id) values ('director', null);
insert into employees(e_name, department_id) values ('security guard', 3);
insert into employees(e_name, department_id) values ('manager IT', 1);
insert into employees(e_name, department_id) values ('manager', 2);
insert into employees(e_name, department_id) values ('programmer', 1);

insert into teens(t_name, gender) values ('Ivan', 1);
insert into teens(t_name, gender) values ('Stepan', 1);
insert into teens(t_name, gender) values ('Oleg', 1);
insert into teens(t_name, gender) values ('Anna', 0);
insert into teens(t_name, gender) values ('Mariya', 0);
insert into teens(t_name, gender) values ('Olga', 0);
insert into teens(t_name, gender) values ('Elena', 0);

select * from employees e left join departments d on e.department_id = d.id;
select * from departments d right join employees e on d.id = e.department_id;
select * from employees e full join departments d on e.department_id = d.id;
select * from employees e cross join departments d;
--3. Используя left join найти департаменты, у которых нет работников
select * from employees e left join departments d on e.department_id = d.id where d.id is null;
--4. Используя left и right join написать запросы, которые давали бы одинаковый результат (порядок вывода колонок
--в эти запросах также должен быть идентичный).
select e_name, d_name from employees e left join departments d on e.department_id = d.id;
select e_name, d_name from departments d right join employees e on d.id = e.department_id;
--5. Используя cross join составить все возможные разнополые пары
select t1.t_name, t2.t_name from teens t1 cross join teens t2 where t1.gender != t2.gender;
--если не повторяться
select t1.t_name, t2.t_name from teens t1 cross join teens t2 where t1.gender > t2.gender;
select t1.t_name, t2.t_name from teens t1 cross join teens t2 where t1.gender < t2.gender;

