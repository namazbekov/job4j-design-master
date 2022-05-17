create table teacher (
	id serial primary key,
	name varchar (255),
	subject varchar (255)
);
create table student (
	id serial primary key,
	name varchar (255),
	cours int,
	teacher_id int references teacher(id)
);

insert into teacher (name, subject) values ('Dmitriy.N', 'mathematics');
insert into teacher (name, subject) values ('Nadezhda.C', 'history');
insert into teacher (name, subject) values ('Petr.V', 'physics');

insert into student (name, cours, teacher_id) values ('Pasha', 1, 1);
insert into student (name, cours, teacher_id) values ('Sasha', 2, 2);
insert into student (name, cours, teacher_id) values ('Masha', 3, 3);

select t.name, t.subject, s.name, s.cours
from student as s
join teacher as t
on s.teacher_id = t.id;

select t.name, s.name, s.cours
from student as s
join teacher as t
on s.teacher_id = t.id;

select t.name as Преподователь, t.subject as Предмет,
s.name as Студент, s.cours as Курс
from student as s
join teacher as t
on s.teacher_id = t.id;