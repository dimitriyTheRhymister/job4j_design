create table car_bodies(
    id serial primary key,
    b_name varchar(55)
);

create table car_engines(
    id serial primary key,
    e_name varchar(55)
);

create table car_transmissions(
    id serial primary key,
    t_name varchar(55)
);

create table cars(
    id serial primary key,
    c_name varchar(55),
    body_id int references car_bodies(id),
    engine_id int references car_bodies(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies(b_name) values ('Седан'), ('Хэтчбек'), ('Универсал'), ('Кабриолет');
insert into car_bodies(b_name) values ('не содержит');
insert into car_engines(e_name) VALUES ('Бензиновый'), ('Дизельный'), ('Гибридный');
insert into car_engines(e_name) VALUES ('не содержит');
insert into car_transmissions(t_name) VALUES ('Механическая'), ('Гидромеханическая'), ('Гидрообъёмная'), ('Электромеханическая');
insert into car_transmissions(t_name) VALUES ('не содержит');
insert into cars(c_name, body_id, engine_id, transmission_id) VALUES 
('Bentley', 1, 2, 3),
('Fiat', 2, null, 1),
('Lancia', 2, 2, 3),
('Peugeot', 3, 1, null),
('Renault', 1, 1, 4),
('Saab', 1, 2, 3),
('Skoda', null, 1, 3),
('Volvo', 3, 1, 4);
insert into cars(c_name, body_id, engine_id, transmission_id) VALUES 
('Ferrari', 5, 1, 3),
('Mini', 2, 4, 3),
('Bugatti', 3, 1, 5);

--1.Вывести список всех машин и все привязанные к ним детали.
select с.c_name, cb.b_name, ce.e_name, ct.t_name
from cars с
left join car_bodies cb on с.body_id = cb.id
left join car_engines ce on с.engine_id = ce.id
left join car_transmissions ct on с.transmission_id = ct.id;

--2.Вывести кузова, которые не используются НИ в одной машине.
select cb.b_name
from car_bodies cb
left join cars c on cb.id = c.body_id
where c.body_id is null;

--3.Вывести двигатели, которые не используются НИ в одной машине.
select ce.e_name
from car_engines ce
left join cars c on ce.id = c.engine_id
where c.engine_id is null;

--4.Вывести коробки передач, которые не используются НИ в одной машине.
select ct.t_name
from car_transmissions ct
left join cars c on ct.id = c.transmission_id
where c.transmission_id is null;