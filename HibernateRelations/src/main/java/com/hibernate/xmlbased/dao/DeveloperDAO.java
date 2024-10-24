package com.hibernate.xmlbased.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.xmlbased.config.SessionConfig;
import com.hibernate.xmlbased.model.Department;
import com.hibernate.xmlbased.model.Developer;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

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

	public Developer updateDevelopersDepartment(Integer devId, Department department) {
		Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Developer developer = session.get(Developer.class, devId);
		developer.setDepartment(department);
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
	
	public List<Developer> findByExperienceEqual(Integer experience) {
		Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Developer> devs = session.createQuery(String.format("FROM Developer WHERE experience = %d", experience),  Developer.class).getResultList();
		transaction.commit();
		session.close();
		return devs;
	}
	
	public List<Developer> findByExperienceEqualCriteria(Integer experience) {
		Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Developer> criteria = criteriaBuilder.createQuery(Developer.class);
		Root<Developer> devCriteria = criteria.from(Developer.class);
		
		criteria.where(criteriaBuilder.equal(devCriteria.get("experience"), experience));
		List<Developer> developers = session.createQuery(criteria).getResultList();

		transaction.commit();
		session.close();
		return developers;
	}
}
