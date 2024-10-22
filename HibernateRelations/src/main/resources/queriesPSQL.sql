CREATE TYPE role AS ENUM ('ROLE_ADMIN', 'ROLE_USER');
	
CREATE TABLE developer
(
	id int GENERATED ALWAYS AS IDENTITY PRIMAKY KEY,
	name varchar(20) NOT NULL,
	specialty varchar(20) NOT NULL,
	experience int not null CHECK (experience > -1),
	department_id char(3) REFERENCES departments ON DELETE CASCADE NOT NULL DEFAULT 'B04'
)

CREATE TABLE departments
(
	department_id char(3) CHECK (LENGTH(department_id) = 3) PRIMARY KEY,
	department_name varchar (100) NOT NULL UNIQUE,
	location text NOT NULL
)

INSERT INTO departments VALUES
	('A01', 'Инженеры', 'rondo Daszyńskiego 1, 00-843 Warszawa'),
	('A07', 'Тестировщики', 'rondo Daszyńskiego 1, 00-843 Warszawa'),
	('B04', 'Очень умные человеки', 'J.E. Irausquin Boulevard 20-A Oranjestad, Aruba')

ALTER TABLE developers ADD COLUMN department_id char(3) REFERENCES departments ON DELETE CASCADE NOT NULL DEFAULT 'B04';
	
CREATE TABLE users
(
	user_id int PRIMARY KEY REFERENCES developers(id),
	username varchar(30) NOT NULL UNIQUE,
	password varchar (30) NOT NULL,
	user_role role NOT NULL
);

