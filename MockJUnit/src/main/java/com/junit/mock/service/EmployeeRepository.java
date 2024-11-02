package com.junit.mock.service;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.junit.mock.HibernateConfig;
import com.junit.mock.model.Employee;

public class EmployeeRepository {
	private static HibernateConfig config = null;

	public EmployeeRepository() {
		config = HibernateConfig.getInstanceOfSeccionFactory();
	}

	public void save(Employee employee) {
		Session session = config.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.persist(employee);
		transaction.commit();
		session.close();
	}

	public Optional<Employee> findById(Integer id) {
		Session session = config.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Optional<Employee> employee = Optional.ofNullable(session.find(Employee.class, id));
		transaction.commit();
		session.close();
		return employee;
	}

	public void delete(Employee employee) {
		Session session = config.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.remove(employee);
		transaction.commit();
		session.close();
	}
}
