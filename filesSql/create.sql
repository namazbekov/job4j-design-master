create table users (
	id serial primary key,
	name varchar(255)
);
create table roles (
	id serial primary key,
	name varchar(255),
	user_id int references users(id)
);
create table rules(
	id serial primary key,
	name varchar(255)
);
create table roles_rules(
	id serial primary key,
	rule_id int references rule(id),
	role_id int references role(id)
);
create table item (
	id serial primary key,
	name varchar(255),
	user_id int references users(id)
);
create table comments (
	id serial primary key,
	name varchar(255),
	item_id int references item(id)
);
create table attach(
	id serial primary key,
	name varchar(255),
	item_id int references item(id)
);
create table category (
	id serial primary key,
	name varchar(255),
	item_id int references item(id)
);
create table state (
	id serial primary key,
	name varchar(255),
	item_id int references item(id)
);