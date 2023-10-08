create table fauna (
    id serial primary key,
    animal_name text,
    avg_age int,
    discovery_date date
);

insert into fauna(animal_name, avg_age, discovery_date) values ('bird_1', 55123, '1820-09-01');
insert into fauna(animal_name, avg_age, discovery_date) values ('fishl_2', 273, '1937-01-11');
update fauna set animal_name = 'fish_2' where avg_age = 273;
insert into fauna(animal_name, avg_age, discovery_date) values ('mammal_3', 13213, null);
insert into fauna(animal_name, avg_age, discovery_date) values ('insect_4', 5238, '1435-12-19');
insert into fauna(animal_name, avg_age, discovery_date) values ('bird_5', 738, '1845-09-07');
insert into fauna(animal_name, avg_age, discovery_date) values ('mammal_6', 523345, '1900-07-25');
insert into fauna(animal_name, avg_age, discovery_date) values ('insect_7', 33231, null);
insert into fauna(animal_name, avg_age, discovery_date) values ('amphibian_8', 3567, '1989-11-08');
insert into fauna(animal_name, avg_age, discovery_date) values ('fish_9', 623, '1867-09-15');
insert into fauna(animal_name, avg_age, discovery_date) values ('amphibianl_10', 199456, '1876-10-21');
update fauna set animal_name = 'amphibian_10' where discovery_date = '1876-10-21';

select * from fauna where animal_name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';
select * from fauna where discovery_date < '01.01.1850';