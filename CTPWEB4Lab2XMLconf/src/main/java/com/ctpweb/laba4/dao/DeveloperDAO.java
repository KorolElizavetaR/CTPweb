package com.ctpweb.laba4.dao;

import java.lang.reflect.Field;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ctpweb.laba4.model.Developer;

public class DeveloperDAO {
	private final SessionFactory sessionFactory;
	private Session session;

	public DeveloperDAO()
	{
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public List<Developer> getDevelopersList()
	{
		session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List <Developer> devs = session.createQuery("FROM Developer", Developer.class).getResultList();
		transaction.commit();
		return devs;
	}
	
	public Developer getDeveloper(Integer id)
	{
		session = sessionFactory.getCurrentSession();
		 Transaction transaction = session.beginTransaction();
		 Developer dev = session.get(Developer.class, id);
		 transaction.commit();
		 return dev;
	}
	
	 public void addDeveloper(Developer developer) {
		 session = sessionFactory.getCurrentSession();
		 Transaction transaction = session.beginTransaction();
		 session.persist(developer);
		 transaction.commit();
	 }
	 
	 public void updateDeveloper(int developerId, Developer dev) throws IllegalArgumentException, IllegalAccessException {
		 session = sessionFactory.getCurrentSession();
		 Transaction transaction = session.beginTransaction();
		 dev.setId(developerId);
		 Field[] devFields = dev.getClass().getDeclaredFields();
		 Integer countFields = devFields.length;
		 Developer developer = session.find(Developer.class, developerId);
		 for (int i = 1; i < countFields; i++)
		 {
			 devFields[i].setAccessible(true);
			 Object value = devFields[i].get(dev);
			 devFields[i].set(developer, value);
			 devFields[i].setAccessible(false);
		 }
		 transaction.commit();
	 }

	 public void removeDeveloper(int developerId) {
		 session = sessionFactory.getCurrentSession();
		 Transaction transaction = session.beginTransaction();
		 Developer developer = session.get(Developer.class, developerId);
		 session.remove(developer);
		 transaction.commit();
	 }
 
}
