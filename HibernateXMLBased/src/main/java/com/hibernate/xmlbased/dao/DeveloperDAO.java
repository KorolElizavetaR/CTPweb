package com.hibernate.xmlbased.dao;

import org.hibernate.cfg.Configuration;

import com.hibernate.xmlbased.model.Developer;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DeveloperDAO {
	private final SessionFactory sessionFactory;
	private Session session;

	public DeveloperDAO() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	public void addDeveloper(Developer developer) {
		session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.persist(developer);
		transaction.commit();
		session.close();
	}

	public Developer getDeveloperById(Integer id) {
		session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Developer developer = session.get(Developer.class, id);
		transaction.commit();
		session.close();
		return developer;
	}

	public List<Developer> getDevelopers() {
		session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Developer> developers = session.createQuery("FROM Developer", Developer.class).list();
		transaction.commit();
		session.close();
		return developers;
	}

	public Developer updateDeveloper(Integer id, Integer experience) {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Developer developer = session.get(Developer.class, id);
		developer.setExperience(experience);
		transaction.commit();
		session.close();
		return developer;
	}

	public void removeDeveloper(Integer id) {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Developer developer = session.get(Developer.class, id);
		session.remove(developer);
		transaction.commit();
		session.close();
	}

}
