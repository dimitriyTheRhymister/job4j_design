create table continents(
    id serial primary key,
    continent_name varchar(255)
);

create table countries(
    id serial primary key,
    country_name varchar(255),
    continent_id int references continents(id)
);

insert into continents(continent_name) values ('North America');
insert into countries(country_name, continent_id) VALUES ('USA', 1);

select * from countries;
select * from continents where id in (select continent_id from countries);