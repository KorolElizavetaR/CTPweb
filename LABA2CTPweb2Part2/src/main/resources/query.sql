CREATE TABLE student
(
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, 
	fname varchar(10) NOT NULL,
	lname varchar(10) NOT NULL,
	address text,
	mobile_no VARCHAR(20) UNIQUE,
	email_id VARCHAR(255) UNIQUE,
	city VARCHAR(40),
	designation VARCHAR(40),
	dob DATE NOT NULL,
	doj DATE,
	salary NUMERIC(10, 2),
	add_date TIMESTAMP DEFAULT NOW(),
	UNIQUE (fname, lname)
);
Truncate table student;
DROP TABLE student;

INSERT INTO student(fname, lname, email_id, city, dob) 
	VALUES ('Kostya', 'Emelyanov', 'amogus@gmail.com', 'Minsk', '10.09.2005'),
			('Masha', 'Frolova', 'frolova2004@gmail.com', 'Minsk', '11.04.2004'),
			('Varvara', 'Kusova', 'varvarvar@gmail.com', 'Minsk', '25.03.2005');

INSERT INTO student(fname, lname, mobile_no, email_id, city, dob) 
	VALUES ('Ivan', 'Petruch', '+375(29)000-00-00', 'aaaaaa@gmail.com', 'Minsk', '10.09.2005'),
			('Alexandr', 'Bush', '+375(29)111-11-11','asdawdasdawd@gmail.com', 'Gomel', '11.04.2004'),
			('Alexandra', 'Solovyova','+375(29)222-22-22', 'asolovyove@gmail.com', 'Gomel', '25.03.2005');

INSERT INTO student(fname, lname, mobile_no, email_id, city, dob, designation, doj, salary) 
	VALUES ('Ivan', 'Boreyko', '+375(29)333-00-33', 'moron@gmail.com', 'Minsk', '14.01.2005', 'Python-developer', '14.12.2024', 390),
			('Vasya', 'Arbuz', '+375(29)125-11-11','arbuz@gmail.com', 'Brest', '01.12.2003', 'DevOps', '23.05.2024', 390),
			('Sonya', 'Koshka','+375(29)234-22-22', 'katsanya@gmail.com', 'Gomel', '30.01.2005', 'Trainee Flutter', '01.09.2024', 450),
			('Maxim', 'Arbuz', '+375(29)126-11-11','maxarbuz@gmail.com', 'Brest', '01.12.2003', 'smh majors in Minecraft', '01.01.2019', 0);
