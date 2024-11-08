package com.junit.mock.service;

import java.util.List;
import java.util.Set;

import com.junit.mock.exception.EmployeeNotFoundException;
import com.junit.mock.model.Employee;
import com.junit.mock.repository.EmployeeRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class EmployeeService {

	private final EmployeeRepository repos;
	
	public EmployeeService(EmployeeRepository repos) {
		this.repos = repos;
	}

	public Employee saveEmployee(Employee employee) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
		if (!violations.isEmpty())
			throw new ConstraintViolationException(violations);
		repos.save(employee);
		Employee emp = findEmployeeByID(employee.getEmployeeId());
		return emp;
	}

	public Employee findEmployeeByID(Integer id) {
		return repos.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}
	
	public List <Employee> getAllEmployees() {
		return repos.findAll();
	}

	public Employee updateEmployee(Employee newEmpl, Integer id) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Employee>> violations = validator.validate(newEmpl);
		if (!violations.isEmpty())
			throw new ConstraintViolationException(violations);
		findEmployeeByID(id);
		Employee newEmployee = repos.update(newEmpl, id).get();
		return newEmployee;
	}
	
	public void removeEmployee(Integer id) {
		Employee employee = repos.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		repos.delete(employee);
	}

}
