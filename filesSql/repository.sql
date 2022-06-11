create table body (
	id serial primary key,
	name varchar (255)
);
create table engine (
	id serial primary key,
	number int
);
create table transmission (
	id serial primary key,
	number int
);
create table car (
	id serial primary key,
	name varchar (255),
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into body (name) values ('крыло');
insert into body (name) values ('дефлектор');
insert into body (name) values ('капот');
insert into body (name) values ('днище');
insert into body (name) values ('спойлер');

insert into engine (number) values (112);
insert into engine (number) values (113);
insert into engine (number) values (114);
insert into engine (number) values (115);
insert into engine (number) values (2222);

insert into transmission (number) values (001);
insert into transmission (number) values (002);
insert into transmission (number) values (003);
insert into transmission (number) values (004);
insert into transmission (number) values (1111);

insert into car (name, body_id, engine_id, transmission_id)
values ('Lada', 1, 2, 3);
insert into car (name, body_id, engine_id, transmission_id)
values ('Ferrary', 4, 1, 3);
insert into car (name, body_id, engine_id, transmission_id)
values ('Toyota', 2, 4, 3);
insert into car (name, body_id, engine_id, transmission_id)
values ('Pontiac', 3, null, 1);
insert into car (name, body_id, engine_id, transmission_id)
values ('Nissan', 1, 1, null);


select c.name, c.body_id as body, c.engine_id as engine, c.transmission_id as transmission
from car c ;

select b.name as body, c.name as car from body b left join car c
on b.id = c.body_id where c.body_id is null;
select e.number as body, c.name as car from engine e left join car c
on e.id = c.engine_id where c.engine_id is null;
select t.number as body, c.name as car from transmission t left join car c
on t.id = c.transmission_id where c.transmission_id is null;
