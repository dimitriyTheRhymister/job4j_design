 create table languages(
     id serial primary key,
     language_name varchar(255)
 );

 create table countries_2(
     id serial primary key,
     country_name varchar(255)
 );

 create table languages_countries_2(
     id serial primary key,
     language_id int references languages(id),
     country_id int references countries_2(id)
 );

insert into languages(language_name) values ('English');
insert into languages(language_name) values ('France');
insert into languages(language_name) values ('German');

insert into countries_2(country_name) values ('Canada');
insert into countries_2(country_name) values ('Switzerland');
insert into countries_2(country_name) values ('USA');

insert into languages_countries_2(language_id, country_id) values (1, 1);
insert into languages_countries_2(language_id, country_id) values (1, 3);
insert into languages_countries_2(language_id, country_id) values (2, 1);
insert into languages_countries_2(language_id, country_id) values (2, 2);
insert into languages_countries_2(language_id, country_id) values (3, 2);

select * from languages;
select * from countries_2;
select * from languages_countries_2;

