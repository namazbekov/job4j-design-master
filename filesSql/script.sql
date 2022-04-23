create table human(
	id serial primary key,
	name varchar (255),
	area text,
	Age INTEGER
);
insert into human(name, area, Age) values('Michael', 'USA', '34');
select * from human;
update human set name = 'Piter';
select * from human;
delete from human;
select * from human;
