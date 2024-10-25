CREATE TABLE employee
(
	employee_id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	empl_name varchar (30) NOT NULL CHECK (length(empl_name)>0),
	empl_dob date NOT NULL CHECK (empl_dob < '01.01.2006'),
	empl_salary numeric(10, 2) CHECK (empl_salary > 0)
)

CREATE TABLE employee (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    empl_name VARCHAR(30) NOT NULL CHECK (CHAR_LENGTH(empl_name) != 0),
    empl_dob DATE NOT NULL CHECK (empl_dob < '2006-01-01'),
    empl_salary DECIMAL(10, 2) CHECK (empl_salary > 0)
);


	
Эту шнягу под mysql написать

DROP TABLE employee;