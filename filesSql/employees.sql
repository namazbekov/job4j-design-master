create table employees(
	id serial primary key,
	name varchar (255)
);

create table carsOfFirm(
	id serial primary key,
	name varchar (255)
);
create table employees_carsOfFirm(
	id serial primary key,
	employe_id int references employees(id),
	cars_id int references carsOfFirm(id)
);
insert into employees(name) values('Dastan');
insert into employees(name) values('Piter');
insert into employees(name) values('Duncan');
insert into carsOfFirm(name) values('Volkswagen Golf');
insert into carsOfFirm(name) values('Volkswagen Polo');
insert into carsOfFirm(name) values('Volkswagen Getta');

insert into employees_carsOfFirm(employe_id, cars_id) values (1, 1);
insert into employees_carsOfFirm(employe_id, cars_id) values (1, 2);
insert into employees_carsOfFirm(employe_id, cars_id) values (1, 3);
select * from employees_carsOfFirm;