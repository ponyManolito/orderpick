create database if not exists orderit;
use orderit;
CREATE TABLE if not exists users (
	id INT(8) AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(60) NOT NULL,
	password VARCHAR(100) NOT NULL,
	reg_date TIMESTAMP
);

CREATE TABLE if not exists roles (
	id INT(8) AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(60) NOT NULL,
	description VARCHAR(100) NOT NULL,
	reg_date TIMESTAMP
);

CREATE TABLE if not exists authorities (
	id INT(8) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	id_user INT(8) NOT NULL,
	id_role INT(8) NOT NULL,
	reg_date TIMESTAMP,
	CONSTRAINT FOREIGN KEY (id_user) REFERENCES users(id),
    CONSTRAINT FOREIGN KEY (id_role) REFERENCES roles(id)
);

insert into users(name,password) values("admin","admin");
insert into roles(name,description) values("ROLE_ADMIN","Role as administrator");
insert into authorities(id_user,id_role) values((select id from users where name ="admin"),(select id from roles where name ="ROLE_ADMIN"));

CREATE TABLE if not exists cf_parameters (
	name VARCHAR(60) NOT NULL PRIMARY KEY,
	value VARCHAR(100) NOT NULL
);

insert into cf_parameters(name,value) values("BASE_URL_IMAGE","/home/ivan/tmp/image");
insert into cf_parameters(name,value) values("BASE_URL_VIDEO","/home/ivan/tmp/image");

CREATE TABLE if not exists cf_tables (
	id INT(8) AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(60) NOT NULL,
	description VARCHAR(100) NOT NULL,
	available VARCHAR(1)
);

CREATE TABLE if not exists cf_products (
	id INT(8) AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(60) NOT NULL,
	description VARCHAR(100) NOT NULL,
	image VARCHAR(100),
	movie VARCHAR(100),
	empty VARCHAR(1),
	price double
);

CREATE TABLE if not exists cf_turns (
	id INT(8) AUTO_INCREMENT PRIMARY KEY,
	number INT(1) NOT NULL,
	time_init TIME NOT NULL,
	time_finish TIME NOT NULL
);

CREATE TABLE if not exists orders (
	id INT(8) AUTO_INCREMENT PRIMARY KEY,
	id_table INT(8) NOT NULL,
	description VARCHAR(100),
	reg_date TIMESTAMP,
	CONSTRAINT FOREIGN KEY (id_table) REFERENCES cf_tables(id)
);

CREATE TABLE if not exists orders_type (
	id INT(8) AUTO_INCREMENT PRIMARY KEY,
	id_order INT(8) NOT NULL,
	order_type VARCHAR(100),
	status VARCHAR(100),
	CONSTRAINT FOREIGN KEY (id_order) REFERENCES orders(id)
);

CREATE TABLE if not exists orders_products (
	id INT(8) AUTO_INCREMENT PRIMARY KEY,
	id_order_type INT(8) NOT NULL,
	id_product INT(8) NOT NULL,
	quantity INT(3) NOT NULL,
	CONSTRAINT FOREIGN KEY (id_order_type) REFERENCES orders_type(id),
	CONSTRAINT FOREIGN KEY (id_product) REFERENCES cf_products(id)
);

CREATE TABLE if not exists stats_orders (
	id INT(8) AUTO_INCREMENT PRIMARY KEY,
	id_turn INT(8) NOT NULL,
	reg_date TIMESTAMP,
	CONSTRAINT FOREIGN KEY (id_turn) REFERENCES cf_turns(id)
);

CREATE TABLE if not exists stats_orders_products (
	id INT(8) AUTO_INCREMENT PRIMARY KEY,
	id_stats_order INT(8) NOT NULL,
	id_product INT(8) NOT NULL,
	quantity INT(3) NOT NULL,
	CONSTRAINT FOREIGN KEY (id_stats_order) REFERENCES stats_orders(id),
	CONSTRAINT FOREIGN KEY (id_product) REFERENCES cf_products(id)
);