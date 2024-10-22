package com.hibernate.xmlbased.dao;

import java.util.Optional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.xmlbased.config.SessionConfig;
import com.hibernate.xmlbased.model.Developer;
import com.hibernate.xmlbased.model.Role;
import com.hibernate.xmlbased.model.User;

import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class UserDAO {
	private SessionConfig sc;

	public UserDAO() {
		sc = SessionConfig.getInstanceOfSeccionFactory();
	}
	
	public User AuthUser(String username, String password) throws NoResultException
	{
		Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
			Root<User> devCriteria = criteria.from(User.class);
			
			criteria.where(criteriaBuilder.and(criteriaBuilder.equal(devCriteria.get("username"), username),
					criteriaBuilder.equal(devCriteria.get("password"), password)));
			
			User user = session.createQuery(criteria).getSingleResult();
			Hibernate.initialize(user.getDeveloper());
			return user;
		}
		finally
		{
			transaction.commit();
			session.close();
		}
	}
	
	public void updatePassword(Integer id, String password)
	{
		Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			session.createMutationQuery(String.format("update User u SET password = '%s' WHERE userId = %d", password, id)).executeUpdate();
		}
		catch (Exception ex)
		{
			throw ex;
		}
		finally
		{
			transaction.commit();
			session.close();
		}
	}
}