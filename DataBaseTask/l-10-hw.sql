CREATE DATABASE carservice-jdbc-task;

\connect carservice-jdbc-task;

create table model_type (
	id int2 generated always as identity primary key,
	name varchar(50) unique not null
);

INSERT INTO model_type (name) VALUES ('Volvo V450');
INSERT INTO model_type (name) VALUES ('Volvo V350');
INSERT INTO model_type (name) VALUES ('MAN M400');
INSERT INTO model_type (name) VALUES ('MAN M300');

create table transport_type (
	id int2 generated always as identity primary key,
	name varchar(50) unique not null
);

INSERT INTO transport_type (name) VALUES ('TRUCK');
INSERT INTO transport_type (name) VALUES ('AUTOMOBILE');

create table client (
	id int4 generated always as identity primary key,
	first_name varchar(30) not null,
	last_name varchar(30) not null
);

INSERT INTO client (first_name, last_name) VALUES ('Vladimir', 'Volodarin');
INSERT INTO client (first_name, last_name) VALUES ('Mihael', 'Gorbachev');

create table transport (
	id int4 generated always as identity primary key,
	model_type_id int2 not null,
	transport_type_id int2 not null,
	client_id int4,

	constraint fk_transport_model_type_id foreign key (model_type_id) references model_type(id),
	constraint fk_transport_transport_type_id foreign key (transport_type_id) references transport_type(id),
	constraint fk_transport_client_id foreign key (client_id) references client(id)
);

INSERT INTO transport (model_type_id, transport_type_id, client_id) VALUES (1, 1, 1);
INSERT INTO transport (model_type_id, transport_type_id, client_id) VALUES (2, 2, 1);
INSERT INTO transport (model_type_id, transport_type_id, client_id) VALUES (3, 1, 2);
INSERT INTO transport (model_type_id, transport_type_id, client_id) VALUES (4, 2, 2);
