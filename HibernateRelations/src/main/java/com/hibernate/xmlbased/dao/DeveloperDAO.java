package com.hibernate.xmlbased.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.xmlbased.config.SessionConfig;
import com.hibernate.xmlbased.model.Developer;

public class DeveloperDAO {
	private SessionConfig sc;
	
	public DeveloperDAO() {
		sc = SessionConfig.getInstanceOfSeccionFactory();
	}

	public void addDeveloper(Developer developer) {
		Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.persist(developer);
		transaction.commit();
		session.close();
	}

	public Developer getDeveloperById(Integer id) {
		Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Developer developer = session.get(Developer.class, id);
		transaction.commit();
		session.close();
		return developer;
	}

	public List<Developer> getDevelopers() {
		Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Developer> developers = session.createQuery("FROM Developer", Developer.class).list();
		transaction.commit();
		session.close();
		return developers;
	}

	public Developer updateDeveloper(Integer id, Integer experience) {
		Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Developer developer = session.get(Developer.class, id);
		developer.setExperience(experience);
		transaction.commit();
		session.close();
		return developer;
	}

	public void removeDeveloper(Integer id) {
		Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Developer developer = session.get(Developer.class, id);
		session.remove(developer);
		transaction.commit();
		session.close();
	}

}