package xml.junit.marshalling;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import jakarta.xml.bind.JAXBException;
import xml.junit.marshalling.dao.EmployeeDAO;
import xml.junit.marshalling.model.Employee;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MarshallingTest {
	private static EmployeeDAO employeeDAO;
	private static HibernateConfig config = null;

	@BeforeAll
	static void configure() {
		employeeDAO = new EmployeeDAO();
	}

	@Test
	@Order(1)
	void creation() {
		try {
			Employee empl = employeeDAO.getEmployeeFromFile("src/main/resources/files/emp1.xml");
			employeeDAO.saveEmployee(empl);
//			
//			PersonXML personXML = EmployeeDAO.getQuery("files/1.xml");
//			assertEquals(personEntity, personXML.getEntity());
		} catch (IOException | JAXBException e) {
			fail(e.getLocalizedMessage());
		}
	}  

//	    @Test
//	    @Order(2)
//	    void update() {
//	        try {
//	            Session session = getSession();
//	            int prev = Objects.requireNonNull(EmployeeDAO.executeXML("files/2.xml", session)).getDutyBound();
//	            EmployeeDAO.executeXML("files/3.xml", session);
//	            int post = Objects.requireNonNull(EmployeeDAO.executeXML("files/2.xml", session)).getDutyBound();
//	            assertNotEquals(prev, post);
//	        } catch (IOException | JAXBException | NullPointerException e) {
//	            fail();
//	        }
//	    }
//
//	    @Test
//	    @Order(3)
//	    void delete() {
//	        try {
//	            Session session = getSession();
//	            EmployeeDAO.executeXML("files/4.xml", session);
//	            PersonEntity p = EmployeeDAO.executeXML("files/2.xml", session);
//	            assertNull(p);
//	        } catch (IOException | JAXBException e) {
//	            fail();
//	        }
//	    }
//
//
//	   @Test
//	  @Order(4)
//	    void toxml() {
//	        PersonEntity personEntity = getSession().find(PersonEntity.class, "900");
//	        PersonXML personXML = new PersonXML(personEntity);
//	        try {
//	            JAXBContext context = JAXBContext.newInstance(PersonXML.class);
//	            Marshaller marshaller = context.createMarshaller();
//	            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//	            BufferedWriter writer = new BufferedWriter(new FileWriter("files/out.xml"));
//	            marshaller.marshal(personXML, writer);
//	            writer.close();
//	        } catch (JAXBException | IOException e) {
//	            fail();
//	        }
//	    }
//
//	    @Test
//	    @Order(5)
//	    void jsontest(){
//	        try {
//	            EmployeeDAO.addFromJson("files/in.json", getSession());
//	            EmployeeDAO.dumpToJson("files/out.json", getSession(), "400");
//	            EmployeeDAO.executeXML("files/5.xml", getSession());
//	        } catch (IOException | JAXBException e) {
//	            fail();
//	        }
//	    }

}
