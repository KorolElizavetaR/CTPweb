create table people(
	 id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  pname varchar(50) NOT NULL,
  phone varchar(50) NOT NULL unique,
  email varchar(50) NOT NULL unique
)

INSERT INTO people (pname, phone, email) VALUES
	('Anna', '+375291234567', 'anna.1.18@gmail.com'),
	('Ivan', '+375331114534', 'ivan@gmail.com'),
	('Nikolai', '+3752998734534', 'nik@gmail.com')
