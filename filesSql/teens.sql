create table teens (
	name varchar (255),
	gender varchar (255)
);
insert into teens (name, gender) values ('Dima', 'man');
insert into teens (name, gender) values ('Nastya', 'wooman');
insert into teens (name, gender) values ('Pashya', 'man');

select n.name, g.gender from teens n cross join teens g