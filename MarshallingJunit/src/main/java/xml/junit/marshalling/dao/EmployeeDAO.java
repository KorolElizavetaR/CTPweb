package xml.junit.marshalling.dao;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import xml.junit.marshalling.HibernateConfig;
import xml.junit.marshalling.model.Employee;

public class EmployeeDAO {
	//Session session = config.sessionFactory.getCurrentSession();
	private static HibernateConfig config = null;

	public EmployeeDAO() {
		config = HibernateConfig.getInstanceOfSeccionFactory();
	}

	public Employee getEmployeeFromFile(String filename) throws IOException, JAXBException {
		StringBuilder strbldr = new StringBuilder();
		ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
		for (String line : lines) {
			strbldr.append(line);
		}
		JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (Employee) jaxbUnmarshaller.unmarshal(new StringReader(strbldr.toString()));
	}

	public Employee saveEmployee(Employee entity) {
		Session session = config.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		Employee emp = session.find(Employee.class, entity.getEmployeeId());
		transaction.commit();
		session.close();
		return emp;
	}
	
	public Employee findEmployeeByID(Integer id) {
    	Session session = config.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		Employee employee = session.get(Employee.class, id);
		transaction.commit();
		session.close();
		return employee;
    }

//	    private static void update(PersonEntity entity, Session session) {
//	        session.beginTransaction();
//	        PersonEntity mod = session.get(PersonEntity.class, entity.getId());
//	        if (entity.getSurname() != null) {
//	            mod.setSurname(entity.getSurname());
//	        }
//	        if (entity.getAddress() != null) {
//	            mod.setAddress(entity.getAddress());
//	        }
//	        mod.setDutyBound(entity.getDutyBound());
//	        session.update(mod);
//	        session.getTransaction().commit();
//	    }
//
//	    private static PersonEntity read(PersonEntity entity, Session session) {
//	        return session.get(PersonEntity.class, entity.getId());
//	    }
//
//	    private static void delete(PersonEntity entity, Session session) {
//	        session.beginTransaction();
//	        PersonEntity myObject = session.load(PersonEntity.class, entity.getId());
//	        session.delete(myObject);
//	        session.getTransaction().commit();
//	    }
//
//	    static void addFromJson(@SuppressWarnings("SameParameterValue") String filename, Session session) throws IOException {
//	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//	        try (Reader reader = new FileReader(filename)) {
//	            PersonEntity personEntity = gson.fromJson(reader, PersonEntity.class);
//	            create(personEntity, session);
//	        }
//	    }
//
//	    static void dumpToJson(@SuppressWarnings("SameParameterValue") String filename, Session session, @SuppressWarnings("SameParameterValue") String ID) throws IOException {
//	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//	        PersonEntity personEntity = session.find(PersonEntity.class, ID);
//	        try (FileWriter writer = new FileWriter(filename)) {
//	            gson.toJson(personEntity, writer);
//	        }
//	    }
//	}

}
