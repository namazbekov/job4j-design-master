create table passTicket(
	id serial primary key,
	seria int,
	number int
);

create table student(
	id serial primary key,
	name varchar(255),
	passTicket_id int references passTicket(id) unique
);

insert into passTicket(seria, number) values('2018', '559460')
insert into student(name, passTicket_id) values('JZ', 1)
select * from student;