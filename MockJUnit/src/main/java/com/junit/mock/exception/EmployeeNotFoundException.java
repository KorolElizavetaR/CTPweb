package com.junit.mock.exception;

public class EmployeeNotFoundException extends RuntimeException{
	public EmployeeNotFoundException(int id) {
		super(String.format("Employee with ID %d is not found.", id));
	}
}
