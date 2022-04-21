create table human(
	id serial primary key,
	name varchar (255),
	area text
);
insert into human(name, area) values('Michael', 'USA');
select * from human;
update human set name = 'Piter';
select * from human;
delete from human;
select * from human;
