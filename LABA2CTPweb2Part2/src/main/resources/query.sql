CREATE TABLE student
(
	id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, 
	fname varchar(10) NOT NULL,
	lname varchar(10) NOT NULL,
	address text,
	mobile_no VARCHAR(20),
	email_id VARCHAR(255) UNIQUE,
	city VARCHAR(40),
	designation VARCHAR(40),
	dob DATE NOT NULL,
	doj DATE,
	salary NUMERIC(10, 2),
	addtime TIMESTAMP DEFAULT NOW(),
	UNIQUE (fname, lname)
)
DROP TABLE student;

INSERT INTO student(fname, lname, email, city, dob)