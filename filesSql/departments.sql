create table departments (
	id serial primary key,
	name varchar (255)
);
create table employees (
	id serial primary key,
	name varchar (255),
	departments_id int references departments(id)
);
insert into departments (name) values ('sale');
insert into departments (name) values ('order');
insert into departments (name) values ('marketing');

insert into employees (name, departments_id) values ('Ivanov.B.B', 1);
insert into employees (name, departments_id) values ('Petrov.A.N', 2);
insert into employees (name, departments_id) values ('Durov D.P', 3);
insert into employees (name, departments_id) values ('Vlasov N.C', 2);
insert into employees (name, departments_id) values (null, 3);
insert into employees (name, departments_id) values (null, 2);

select * from departments d left join employees e
on d.id = e.departments_id where e is null;

select d.name, e.name, e.departments_id from employees e left join departments d
on e.departments_id = d.id;
select d.name, e.name, e.departments_id from employees e right join departments d
on e.departments_id = d.id;