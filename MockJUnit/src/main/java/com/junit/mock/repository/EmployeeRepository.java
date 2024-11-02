package com.junit.mock.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.junit.mock.model.Employee;

public class EmployeeRepository {
	private static SessionFactory sessionFactory = null;

	public EmployeeRepository() {
		sessionFactory = new Configuration().addAnnotatedClass(Employee.class).configure().buildSessionFactory();
	}

	public void save(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.persist(employee);
		transaction.commit();
		session.close();
	}

	public Optional<Employee> findById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Optional<Employee> employee = Optional.ofNullable(session.find(Employee.class, id));
		transaction.commit();
		session.close();
		return employee;
	}

	public void delete(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.remove(employee);
		transaction.commit();
		session.close();
	}

	public Optional<Employee> update(Employee newEmployee, Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Optional<Employee> employee = Optional.ofNullable(session.find(Employee.class, id));
		employee.get().setEmplName(newEmployee.getEmplName()).setEmplDob(newEmployee.getEmplDob())
				.setEmplSalary(newEmployee.getEmplSalary());
		transaction.commit();
		session.close();
		return employee;
	}

	public List <Employee> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List <Employee> employees = session.createQuery("FROM Employee", Employee.class).list();
		transaction.commit();
		session.close();
		return employees;
	}
}
