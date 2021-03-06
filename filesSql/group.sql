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
insert into people (name) values ('Dima'), ('Pashya'), ('Vanya');

insert into devices_people (devices_id, people_id) values (1, 1), (2, 1), (3, 1);
insert into devices_people (devices_id, people_id) values (2, 2), (2, 2), (2, 2);
insert into devices_people (devices_id, people_id) values (1, 3), (1, 3), (1, 3);

select avg(price) from devices;

select p.name, avg(d.price)
from devices_people
join devices as d
on d.id = devices_id
join people as p
on p.id = people_id
group by p.name
having avg(d.price) > 5000;

