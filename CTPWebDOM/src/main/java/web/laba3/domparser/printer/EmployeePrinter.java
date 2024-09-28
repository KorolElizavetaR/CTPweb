package web.laba3.domparser.printer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.management.AttributeNotFoundException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import web.laba3.domparser.model.Employee;
import web.laba3.domparser.service.EmployeeService;

@RequiredArgsConstructor (access = AccessLevel.PRIVATE)
public class EmployeePrinter {
	private final EmployeeService employeeService;
	private final Document document;
	private static EmployeePrinter employeePrinter;
	
	public static EmployeePrinter getInstanceOfEmployeePrinter() throws SAXException, IOException, ParserConfigurationException
	{
		if (employeePrinter == null)
			return new EmployeePrinter(new EmployeeService(), buildDocument());
		return employeePrinter;
	}
	
	private static Document buildDocument() throws SAXException, IOException, ParserConfigurationException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document =  builder.parse(new File("src\\main\\resources\\employee.xml"));
		return document;
	}
	
	public void printEmployees() {
		System.out.println("\tprintEmployees()");
		List <Employee> employees = EmployeeService.getEmployees(document);
		for (Employee employee : employees) {
			System.out.println(employee);
		}
	}
	
	public void printEmployeeById(Integer id) throws AttributeNotFoundException {
		System.out.println("\tprintEmployeeById(Integer id) ");
		System.out.println(EmployeeService.getEmployeeById(document, id));
	}
	
	public void addEmployee(Employee employee) throws AttributeNotFoundException, TransformerException
	{
		System.out.println("\taddEmployee(Employee employee)");
		System.out.println(EmployeeService.addEmployee(document, employee));
	}
	
	public void deleteEmployee(Integer id) throws TransformerException
	{
		System.out.println("\tdeleteEmployee(Integer id)");
		System.out.println(EmployeeService.deleteEmployee(document, id));
	}
}
