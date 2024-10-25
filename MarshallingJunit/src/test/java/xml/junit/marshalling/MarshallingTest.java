package xml.junit.marshalling;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
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
	void createValidEmployeeTest() {
		try {
			Employee empl = employeeDAO.getEmployeeFromXML("src/main/resources/files/emp1.xml");
			employeeDAO.saveEmployee(empl);
		} catch (IOException | JAXBException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@Order(2)
	void createInvalidEmployeeTest() {
		try {
			Employee empl = employeeDAO.getEmployeeFromXML("src/main/resources/files/emp2.xml");
			assertThrows(ConstraintViolationException.class, () -> employeeDAO.saveEmployee(empl));
		} catch (IOException | JAXBException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@Order(3)
	void updateEmployeeTest() {
		try {
			Employee empl = employeeDAO.getEmployeeFromXML("src/main/resources/files/emp3.xml");
			employeeDAO.updateEmployee(empl, 3);
		} catch (IOException | JAXBException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@Order(4)
	void employeeToXMLTest() {
		Employee emp = employeeDAO.findEmployeeByID(5);
		try {
			JAXBContext context = JAXBContext.newInstance(Employee.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/files/out.xml"));
			marshaller.marshal(emp, writer);
			writer.close();
		} catch (JAXBException | IOException e) {
			fail();
		}
	}

	@Test
	@Order(5)
	void toJsonTest() throws Exception {
		employeeDAO.dumpEmployeeToJson("src/main/resources/files/out.json", employeeDAO.findEmployeeByID(3));
	}

	@Test
	@Order(6)
	void jsontest() throws IOException {
		employeeDAO.saveEmployee(employeeDAO.getEmployeeFromJson("src/main/resources/files/in.json"));
	}
}
