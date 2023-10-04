 create table capitals_2(
     id serial primary key,
     capital_name varchar(255)
 );

 create table countries_4(
     id serial primary key,
     country_name varchar(255),
     capital_id int references capitals_2(id) unique
 );

insert into capitals_2(capital_name) values ('London');
insert into capitals_2(capital_name) values ('Washington');

insert into countries_4(country_name, capital_id) values ('Great Britain', 1);
insert into countries_4(country_name, capital_id) values ('USA', 2);

select * from capitals_2;
select * from countries_4;


