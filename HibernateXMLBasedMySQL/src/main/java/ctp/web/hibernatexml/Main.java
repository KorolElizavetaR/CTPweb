package ctp.web.hibernatexml;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import ctp.web.hibernatexml.model.Developer;

public class Main {

	    public static void main(String[] args) {
	    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	        Session session = sessionFactory.getCurrentSession();
	        Transaction transaction = session.beginTransaction();
	        
	        Developer developer = new Developer("Ivan", "Python-разраб", 2);
	        session.persist(developer);
	        transaction.commit();
	        session.close();

	        session = sessionFactory.getCurrentSession();
	        transaction = session.beginTransaction();
	        System.out.println(session.find(Developer.class, 1)); 
	        transaction.commit();
	        session.close();
	    }
//
//	    public void addDeveloper(String name String specialty, int experience) {
//	        Session session = sessionFactory.openSession();
//	        Transaction transaction = null;
//
//	        transaction = session.beginTransaction();
//	        Developer developer = new Developer(firstName, lastName, specialty, experience);
//	        session.save(developer);
//	        transaction.commit();
//	        session.close();
//	    }
//
//	    public List<Developer> listDevelopers() {
//	        Session session = this.sessionFactory.openSession();
//	        Transaction transaction = null;
//
//	        transaction = session.beginTransaction();
//	        List<Developer> developers = session.createQuery("FROM Developer").list();
//
//	        transaction.commit();
//	        session.close();
//	        return developers;
//	    }
//
//	    public void updateDeveloper(int developerId, int experience) {
//	        Session session = this.sessionFactory.openSession();
//	        Transaction transaction = null;
//
//	        transaction = session.beginTransaction();
//	        Developer developer = (Developer) session.get(Developer.class, developerId);
//	        developer.setExperience(experience);
//	        session.update(developer);
//	        transaction.commit();
//	        session.close();
//	    }
//
//	    public void removeDeveloper(int developerId) {
//	        Session session = this.sessionFactory.openSession();
//	        Transaction transaction = null;
//
//	        transaction = session.beginTransaction();
//	        Developer developer = (Developer) session.get(Developer.class, developerId);
//	        session.delete(developer);
//	        transaction.commit();
//	        session.close();
//	    }
//
}
