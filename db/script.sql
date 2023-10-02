create table sovereigns(
    id serial primary key,
    name varchar(255),
    age int2,
    gender boolean,
    biography text
);
select * from sovereigns;
insert into sovereigns(name, age, gender, biography)
    values('Ivan', 25, '1',
	'Ivan IV Vasilyevich, nicknamed the Terrible[a], tonsured -
	Jonah (August 25, 1530, the village of Kolomenskoye[7] near
	Moscow - March 18 (28), 1584, Moscow) - sovereign, Grand Duke
	of Moscow and All Russia since 1533, the first crowned Tsar
	of All Russia (since 1547; except 1575-1576, when Simeon
	Bekbulatovich was nominally the “Grand Duke of All Russia”).'
    );
select * from sovereigns;
update sovereigns set name = 'Ivan IV Vasilyevich';
select * from sovereigns;
delete from sovereigns;
select * from sovereigns;