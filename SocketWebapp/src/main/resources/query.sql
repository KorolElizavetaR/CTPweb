CREATE TABLE users
(
	id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	username varchar(15) NOT NULL UNIQUE,
	password text NOT NULL
);
DROP TABLE users;
insert into users(username, "password") values ('user1', 'password');