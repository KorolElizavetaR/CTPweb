package com.junit.mock.dao;

import java.util.Set;

import com.junit.mock.exception.EmployeeNotFoundException;
import com.junit.mock.model.Employee;
import com.junit.mock.service.EmployeeRepository;

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
		Employee emp;
		Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
		if (!violations.isEmpty())
			throw new ConstraintViolationException(violations);
		service.save(employee);
		emp = findEmployeeByID(employee.getEmployeeId());
		return emp;
	}

	public Employee findEmployeeByID(Integer id) {
		return service.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}
//
//	public Employee updateEmployee(Employee newEmpl, Integer id) {
//		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//		Session session = config.getSessionFactory().getCurrentSession();
//		Transaction transaction = session.beginTransaction();
//		Employee oldEmp;
//		try {
//			Set<ConstraintViolation<Employee>> violations = validator.validate(newEmpl);
//			if (!violations.isEmpty())
//				throw new ConstraintViolationException(violations);
//			oldEmp = Optional.of(session.find(Employee.class, id)).orElseThrow(() -> new EmployeeNotFoundException(id));
//			oldEmp.setEmplName(newEmpl.getEmplName());
//			oldEmp.setEmplDob(newEmpl.getEmplDob());
//			oldEmp.setEmplSalary(newEmpl.getEmplSalary());
//		} finally {
//			transaction.commit();
//			session.close();
//		}
//		return oldEmp;
//	}
//
//	public List<Employee> getEmployees() {
//		Session session = config.getSessionFactory().getCurrentSession();
//		Transaction transaction = session.beginTransaction();
//		List<Employee> empl = session.createQuery("FROM Employee", Employee.class).list();
//		transaction.commit();
//		session.close();
//		return empl;
//	}
//
//	public void removeEmployee(Integer id) {
//		Session session = config.getSessionFactory().getCurrentSession();
//		Transaction transaction = session.beginTransaction();
//		Employee empl;
//		try {
//			empl = Optional.of(session.find(Employee.class, id)).orElseThrow(() -> new EmployeeNotFoundException(id));
//			session.remove(empl);
//		} finally {
//			transaction.commit();
//			session.close();
//		}
//	}

}
