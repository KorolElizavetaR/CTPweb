package com.ctpweb.laba4.dao;

import java.lang.reflect.Field;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ctpweb.laba4.model.Developer;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeveloperDAO {
	private final SessionFactory sessionFactory;
	private Session session;

	public DeveloperDAO()
	{
		sessionFactory = new Configuration().addAnnotatedClass(Developer.class).buildSessionFactory();
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
			 if (value != null)
			 {
				 devFields[i].set(developer, value); 
			 }
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
	 
	 public List<Developer> getDevsWithSalaryNotLessThan (Integer minSal)
	 {
		 session = sessionFactory.getCurrentSession();
		 Transaction transaction = session.beginTransaction();
		 CriteriaBuilder builder = session.getCriteriaBuilder();
		 CriteriaQuery <Developer> query = builder.createQuery(Developer.class);
		 Root<Developer> root = query.from(Developer.class);
		 query.select(root).where(builder.gt(root.get("salary"), minSal));
		 List<Developer> developers = session.createQuery(query).getResultList();
		 transaction.commit();
		 return developers;
	 }
	 
	 public Integer totalSalary()
	 {
		 session = sessionFactory.getCurrentSession();
		 Transaction transaction = session.beginTransaction();
		 CriteriaBuilder builder = session.getCriteriaBuilder();
		 CriteriaQuery <Integer> query = builder.createQuery(Integer.class);
		 Root<Developer> root = query.from(Developer.class);
		 query.select(builder.sum(root.get("salary")));
		 Integer salary = session.createQuery(query).getSingleResult();
		 transaction.commit();
		 return salary;
	 }
 
}
