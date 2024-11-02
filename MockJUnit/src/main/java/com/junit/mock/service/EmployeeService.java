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

	private final EmployeeRepository service;
	
	public EmployeeService(EmployeeRepository service) {
		this.service = service;
	}

	public Employee saveEmployee(Employee employee) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
		if (!violations.isEmpty())
			throw new ConstraintViolationException(violations);
		service.save(employee);
		Employee emp = findEmployeeByID(employee.getEmployeeId());
		return emp;
	}

	public Employee findEmployeeByID(Integer id) {
		return service.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}
	
	public List <Employee> getAllEmployees() {
		return service.findAll();
	}

	public Employee updateEmployee(Employee newEmpl, Integer id) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Employee>> violations = validator.validate(newEmpl);
		if (!violations.isEmpty())
			throw new ConstraintViolationException(violations);
		findEmployeeByID(id);
		Employee newEmployee = service.update(newEmpl, id).get();
		return newEmployee;
	}
	
	public void removeEmployee(Integer id) {
		Employee employee = service.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
		service.delete(employee);
	}

}
