create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into people(name) values ('Аня'), ('Ваня'), ('Боря');
insert into devices(name, price) values ('Comp', 1000), ('Note', 500), ('iPad', 400), ('iPhone', 300), ('iPod', 200);
insert into devices_people(device_id, people_id) values (1, 1), (1, 2), (1, 3);
insert into devices_people(device_id, people_id) values (2, 1), (2, 2);
insert into devices_people(device_id, people_id) values (3, 1), (3, 2), (3, 3);
insert into devices_people(device_id, people_id) values (4, 2), (4, 3);
insert into devices_people(device_id, people_id) values (5, 1), (5, 2), (5, 3);

select avg(price) from devices;
select  sum(price) from devices;
select  min(price) from devices;
select  count(name) from devices;
select  count(name) from people;

select  people.name, count(devices_people.id)
from people
join devices_people
on devices_people.people_id = people.id
group by people.name;

select  devices.name, count(devices_people.id)
from devices
join devices_people
on devices_people.device_id = devices.id
group by devices.name;

select  p.name, avg(d.price)
from devices_people as dp
join people p
on dp.people_id = p.id
join devices d
on dp.device_id = d.id
group by p.name;

select  p.name, avg(d.price)
from devices_people as dp
join people p
on dp.people_id = p.id
join devices d
on dp.device_id = d.id
group by p.name
having avg(d.price) > 500;