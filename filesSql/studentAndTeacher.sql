create table teacher(
	id serial primary key,
	name varchar(255)
);

create table student(
	id serial primary key,
	name varchar(255),
 	teacher_id int references teacher(id)
);
insert into teacher(name) values('Alex');
insert into student(name, teacher_id) values('Ivan', 1);
select * from teacher;
select * from teacher where id in (select id from student);
select * from passTicket where id in (select id from student);