 create table capitals(
     id serial primary key,
     capital_name varchar(255)
 );

 create table countries_3(
     id serial primary key,
     country_name varchar(255)
 );

 create table capitals_countries_3(
     id serial primary key,
     capital_id int references capitals(id) unique,
     country_id int references countries_3(id) unique
 );

insert into capitals(capital_name) values ('London');
insert into capitals(capital_name) values ('Mexico City');

insert into countries_3(country_name) values ('Great Britain');
insert into countries_3(country_name) values ('Mexico');

insert into capitals_countries_3(capital_id, country_id) values (1, 1);
insert into capitals_countries_3(capital_id, country_id) values (2, 2);

select * from capitals;
select * from countries_3;
select * from capitals_countries_3;


