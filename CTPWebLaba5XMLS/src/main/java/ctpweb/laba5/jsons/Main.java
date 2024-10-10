package ctpweb.laba5.jsons;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import ctpweb.laba5.jsons.model.Person;

public class Main {
	public static void main(String[] args) {
		Configuration configuration = new Configuration().addAnnotatedClass(Person.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Person human = session.get(Person.class, "2");
		session.remove(human);
		session.getTransaction().commit();
	}
}
