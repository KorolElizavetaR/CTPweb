package web.laba3.domparser;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import web.laba3.domparser.model.Employee;
import web.laba3.domparser.printer.EmployeePrinter;

public class Main {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		try
		{
			EmployeePrinter printer = EmployeePrinter.getInstanceOfEmployeePrinter();
			printer.printEmployees();
			printer.printEmployeeById(2);
			printer.addEmployee((new Employee()).
										setAge(28).
										setGender("Female").
										setName("Evgenia").
										setRole("Economist"));
		//	printer.deleteEmployee(7);
			printer.printEmployees();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
