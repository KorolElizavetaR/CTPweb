package com.hibernate.xmlbased.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.xmlbased.config.SessionConfig;
import com.hibernate.xmlbased.model.Department;
import com.hibernate.xmlbased.model.Developer;

public class DepartmentDAO {
	private SessionConfig sc;
	
	public DepartmentDAO() {
		sc = SessionConfig.getInstanceOfSeccionFactory();
	}
	
	public Set<Department> getDepartmentWithWorkers()
	{
		Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Set<Department> departments = new HashSet<>(session.createQuery("FROM Department d LEFT JOIN FETCH d.developers", Department.class).getResultList());
		transaction.commit();
		session.close();
		return departments;
	}
	
	public List<Developer> getDevelopersByDepartment(Department department){
		Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		// n+1 нюанс?
		Department dep = session.get(Department.class, department.getDepartmentId());
		Hibernate.initialize(dep.getDevelopers());
		List<Developer> devs = dep.getDevelopers();
		
		transaction.commit();
		session.close();
		
		return devs;
	}
}
