package web.parser.jaxb;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import web.parser.jaxb.model.Department;
import web.parser.jaxb.model.Employee;
import web.parser.jaxb.model.Organisation;

public class Main {
	private static final String XML_FILE = "dept-info.xml";
	public static void main(String[] args) {
		Organisation org = Organisation.createData();
		try
		{
			JAXBContext context = JAXBContext.newInstance(Organisation.class);
		    Marshaller m = context.createMarshaller();
		    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		    m.marshal(org, System.out);
		    m.marshal(org, new File(XML_FILE));
		    Unmarshaller um = context.createUnmarshaller();
		    Organisation orgFromFile1 = (Organisation) um.unmarshal(new FileReader(XML_FILE));
		    List<Department> depts = orgFromFile1.getDepartments();
		    depts.stream().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
