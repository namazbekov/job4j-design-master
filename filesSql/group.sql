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
    devices_id int references devices(id),
    people_id int references people(id)
);
insert into devices (name, price)
values ('Samsung', 50000), ('MI', 12000), ('Iphone', 130000);
insert into people (name) values ('Dima');
insert into people (name) values ('Pashya');
insert into people (name) values ('Vanya');
insert into devices_people (devices_id, people_id) values (1, 1);
insert into devices_people (devices_id, people_id) values (2, 1);
insert into devices_people (devices_id, people_id) values (3, 2);
insert into devices_people (devices_id, people_id) values (1, 2);
insert into devices_people (devices_id, people_id) values (2, 3);
insert into devices_people (devices_id, people_id) values (1, 3);
insert into devices_people (devices_id, people_id) values (3, 3);

select avg(d.price), p.name, dp.devices_id
from devices as d
join devices_people as dp
on d.id = dp.devices_id
join people as p
on p.id = dp.devices_id
group by p.name, dp.devices_id
