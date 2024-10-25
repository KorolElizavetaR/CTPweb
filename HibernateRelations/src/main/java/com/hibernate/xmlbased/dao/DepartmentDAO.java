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
	
	public List<Department> getDepartments()
	{
		Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List <Department> deps = session.createQuery("FROM Department", Department.class).getResultList();
		transaction.commit();
		session.close();
		return deps;
	}
	
	// этот кусок кода решает проблему n+1
	public Set<Department> getDepartmentWithWorkers()
	{
		Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Set<Department> departments = new HashSet<>(session.createQuery("FROM Department d LEFT JOIN FETCH d.developers", Department.class).getResultList());
		transaction.commit();
		session.close();
		return departments;
	}
	
	// В этом куске кода проблема n+1 не решается
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
	
	public Department findDepartmentByID(String departmentID) {
		Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Department dep = session.find(Department.class, departmentID);
		if (dep == null)
		{
			transaction.commit();
			session.close();
			throw new NullPointerException("Департмент не найден");
		}
		transaction.commit();
		session.close();
		return dep;
	}
	
	public Department addDepartament(Department dep)
	{
		Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.persist(dep);
		Department department = session.find(Department.class, dep.getDepartmentId());
		transaction.commit();
		session.close();
		return department;
	}
	
	public void deleteDepartment(String ID)
	{
		Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Department dep = session.get(Department.class, ID);
		if (dep == null)
		{
			transaction.commit();
			session.close();
			throw new NullPointerException("Департмент не найден");
		}
		session.remove(dep);
		transaction.commit();
		session.close();
	}
	
	public List<Department> findDepartmentByLetterInID(Character charID) {
		Session session = sc.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Department> deps = session.createQuery(String.format("FROM Department WHERE departmentId LIKE '%c%%'", charID), Department.class).getResultList();
		transaction.commit();
		session.close();
		return deps;
	}
}
