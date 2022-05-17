create table roles (
	id serial primary key,
	name varchar(255),
);
create table users (
	id serial primary key,
	name varchar(255),
	roles_id int references roles(id)
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
create table state (
	id serial primary key,
	name varchar(255)
);
create table category (
	id serial primary key,
	name varchar(255)
);
create table item (
	id serial primary key,
	name varchar(255),
	user_id int references users(id),
	state_id int references state(id),
	category_id int references category(id)
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

