package com.hibernate.xmlbased.dao;

import org.hibernate.cfg.Configuration;

import com.hibernate.xmlbased.model.Developer;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

// https://www.youtube.com/watch?v=NDPFZ09BjiI 

public class DeveloperDAO {
	private final SessionFactory sessionFactory;
	private Session session;

	public DeveloperDAO() {
		sessionFactory = new Configuration().addAnnotatedClass(Developer.class).configure().buildSessionFactory();
	}

	// найдем всех сотрудников
	public List<Developer> findAllDevs() {
		session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Developer> criteria = criteriaBuilder.createQuery(Developer.class);
		Root<Developer> devCriteria = criteria.from(Developer.class);
		List <Developer> developers = session.createQuery(criteria).getResultList();
		transaction.commit();
		session.close();
		return developers;
	}
	
	// найти сотрудника по имени
	public Developer findByName(String name) {
		session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Developer> criteria = criteriaBuilder.createQuery(Developer.class);
		Root<Developer> devCriteria = criteria.from(Developer.class);
		
		criteria.where(criteriaBuilder.equal(devCriteria.get("name"), name));
		Developer developer = session.createQuery(criteria).getSingleResult();
		
		transaction.commit();
		session.close();
		return developer;
	}
	
	// найти сотрудников, имя которого содержит подстроку
	public List<Developer> findByNameLike(String nameSubstr) {
		session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Developer> criteria = criteriaBuilder.createQuery(Developer.class);
		Root<Developer> devCriteria = criteria.from(Developer.class);
		
		criteria.where(criteriaBuilder.like(devCriteria.get("name"), String.format("%%%s%%", nameSubstr)));
		List<Developer> developers = session.createQuery(criteria).getResultList();
		
		transaction.commit();
		session.close();
		return developers;
	}
	
	// найти сотрудников с опытом больше указанного значения, относящихся к специальностям
		public List<Developer> findByExperienceGreaterThanAndSpecialtyIn(Integer experience, ArrayList <String> specialty) {
			session = sessionFactory.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Developer> criteria = criteriaBuilder.createQuery(Developer.class);
			Root<Developer> devCriteria = criteria.from(Developer.class);
			
			criteria.where(criteriaBuilder.and(criteriaBuilder.
										gt(devCriteria.get("experience"), experience), 
										devCriteria.get("specialty").in(specialty)));
			List<Developer> developers = session.createQuery(criteria).getResultList();
			
			transaction.commit();
			session.close();
			return developers;
		}
		
		// удалить сотрудника по имени
		public void deleteDevByName(String name)
		{
			session = sessionFactory.getCurrentSession();
			Transaction transaction = session.beginTransaction();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaDelete<Developer> criteria = criteriaBuilder.createCriteriaDelete(Developer.class);
			Root<Developer> devCriteria = criteria.from(Developer.class);
			criteria.where(criteriaBuilder.equal(devCriteria.get("name"), name));
			session.createMutationQuery(criteria).executeUpdate();
			transaction.commit();
			session.close();
		}

}
