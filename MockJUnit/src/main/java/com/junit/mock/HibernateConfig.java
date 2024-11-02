package com.junit.mock;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.junit.mock.model.Employee;

public class HibernateConfig {
	private static HibernateConfig sc;
	final SessionFactory sessionFactory;

	private HibernateConfig() {
		sessionFactory = new Configuration().addAnnotatedClass(Employee.class).configure().buildSessionFactory();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static HibernateConfig getInstanceOfSeccionFactory() {
		if (sc == null)
			sc = new HibernateConfig();
		return sc;
	}
}
