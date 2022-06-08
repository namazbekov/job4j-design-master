create table type (
	id serial primary key,
	name varchar (255)
);
create table product (
	id serial primary key,
	name varchar (255),
	type_id int references type(id),
	expried_date timestamp,
	price int
);

insert into type (name) values ('Сыр');
insert into type (name) values ('Мясо');
insert into type (name) values ('Молоко');

insert into product (name, type_id, expried_date, price) 
values ('Хохлфнд', 1, date '12-06-2022', 150);
insert into product (name, type_id, expried_date, price) 
values ('Голандский сыр', 1, date '24-06-2022', 600);
insert into product (name, type_id, expried_date, price) 
values ('Грузинский сыр', 1, date '15-06-2022', 350);
insert into product (name, type_id, expried_date, price) 
values ('Черкизовские сосиски', 2, date '18-06-2022', 325);
insert into product (name, type_id, expried_date, price) 
values ('Краковская говядина', 2, date '19-06-2022', 450);
insert into product (name, type_id, expried_date, price) 
values ('Ставрополькая курица', 2, date '20-06-2022', 140);
insert into product (name, type_id, expried_date, price) 
values ('Вкуснотеевское молоко', 3, date '21-06-2022', 75);
insert into product (name, type_id, expried_date, price) 
values ('ЭкониваАгро молоко', 3, date '12-07-2022', 65);
insert into product (name, type_id, expried_date, price) 
values ('Хохольское молоко', 3, date '23-06-2022', 80);

select * from product 
join type t on product.type_id = t.id where t.name = 'Сыр';

select * from product where name like 'мороженное';

select expried_date < '08-06-2022' from product;

select max(price) from product;

select * from product 
join type t on product.type_id = t.id
where t.name = 'Сыр' or t.name = 'Молоко';

select type.name, count(product.name) as count
from type join product on product.type_id = type_id
group by type.name having count(product.name) < 3;

select * from type join product on type.id = product.type_id