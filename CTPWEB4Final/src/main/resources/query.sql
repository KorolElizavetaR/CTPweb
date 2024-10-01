CREATE TABLE companies (
   company_id int GENERATED ALWAYS AS IDENTITY primary key,
   company_name varchar(45) NOT NULL,
   company_country varchar(45) NOT NULL);
DROP TABLE companies;

 CREATE TABLE cars (
   car_id int GENERATED ALWAYS AS IDENTITY primary key,
   name varchar(45) NOT NULL,
   year int NOT NULL,
   distance int DEFAULT NULL,
   fuel varchar(45) NOT NULL DEFAULT 'Бензин',
   fuel_consumption varchar(45) NOT NULL,
   price int NOT NULL,
   company_id int NOT NULL,
   FOREIGN KEY (company_id) REFERENCES companies(company_id)
   ON delete cascade
 );
DROP TABLE cars;

CREATE TABLE people (
  person_id int primary key GENERATED ALWAYS AS IDENTITY,
  surname varchar(45) NOT NULL,
  name varchar(45) NOT NULL,
  age int NOT NULL,
  phone varchar(45) NOT NULL,
  mail varchar(45) NOT NULL
);
SELECT * FROM people;

CREATE TABLE users (
  user_id int primary key GENERATED ALWAYS AS IDENTITY,
  login varchar(45) NOT NULL,
  password varchar(256) NOT NULL,
  role varchar(45) not null DEFAULT 'User',
  person_id int,

 constraint fk_user_person foreign key (person_id) references people (person_id)
 on delete cascade
);
DROP TABLE users;

INSERT INTO people(surname, name, age, phone, mail) VALUES 
	('Elizaveta', 'Korol', 19, '+375(44)000-00-00', 'elizavetakorol2005@gmail.com'),
	('Qwerty', 'Qwerty', 21, '+375(29)000-00-00', 'qwerty@gmail.com')
	
INSERT INTO users(login, password, role, person_id) VALUES 
	('liza', '$2a$12$6cMRcODDyGjEcDKSLLasUuFSrkkqtmTZug/TIyQ3wt7Y.j4KrZtfW', 'ROLE_ADMIN', 1),
	('qwerty', '$2a$12$qszdXUlshSeMYjwzWOZURuwmFVHgy9XhO720OREZa388yusIUJ/nG', 'ROLE_USER', 2)
