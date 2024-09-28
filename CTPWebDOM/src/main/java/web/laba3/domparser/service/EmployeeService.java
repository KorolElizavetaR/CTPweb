package web.laba3.domparser.service;

import java.util.ArrayList;
import java.util.List;

import javax.management.AttributeNotFoundException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import web.laba3.domparser.model.Employee;

public class EmployeeService {
	static private NodeList list;
	static private int length;
	static String[] properties = new String[] {"name", "gender", "age", "role"};
	
	private static void updateNodeList(Document document)
	{
		list = document.getElementsByTagName("Employee");
		length = list.getLength();
	}
	
	public static List <Employee> getEmployees(Document document)
	{
		updateNodeList(document);
	    List<Employee> employees = new ArrayList<>();
	    for (int i = 0; i < length; i++) {
		    Node node = list.item(i);
		    if (node.getNodeType() == Node.ELEMENT_NODE) {
		    	employees.add(getEmployee((Element) node));
		    }
	    }
		return employees;
	}
	
	private static Employee getEmployee(Element element) {
	      return new Employee().setId(Integer.valueOf(element.getAttribute("id")))
	      	.setName(getEmployeeDetails(element, "name"))
	      	.setAge(Integer.valueOf(getEmployeeDetails(element, "age")))
	      	.setGender(getEmployeeDetails(element, "gender"))
	      	.setRole(getEmployeeDetails(element, "role"));
	}
	
	private static String getEmployeeDetails(Element element, String property) {
	      String value = element.getElementsByTagName(property).item(0).getTextContent();
	      return value;
	 }

	public static Employee getEmployeeById(Document document, Integer id) throws AttributeNotFoundException {
		updateNodeList(document);
		if (id > length)
			throw new ArrayIndexOutOfBoundsException();
		for (int i = 0; i < length; i++) {
			Node node = list.item(i);
	         if (node.getNodeType() == Node.ELEMENT_NODE) {
	            Element element = (Element) node;
	            if (String.valueOf(id).equals(element.getAttribute("id"))) {
	            	return getEmployee(element);
	            }
	         }
	      }
		throw new AttributeNotFoundException();
	}
	
	public static Employee addEmployee(Document document, Employee employee) throws AttributeNotFoundException, TransformerException
	{
		employee.setId(length+1);
		Element element = document.createElement("Employee");
		updateNodeList(document);
		document.getDocumentElement().appendChild(setEmployeeStats(element, employee, document));
		TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);
        StreamResult sResult = new StreamResult(document.getBaseURI());
        transformer.transform(source, sResult);
	    return getEmployeeById(document, employee.getId());
	}
	
	private static Element setEmployeeStats(Element element, Employee employee, Document document)
	{
		element.setAttribute("id", String.valueOf(employee.getId()));
	    Element name = getPropertyNode("name", document, employee.getName());
	    element.appendChild(name);
	      Element gender = getPropertyNode("gender", document, employee.getGender());
	     element.appendChild(gender);
	      Element age = getPropertyNode("age", document, String.valueOf(employee.getAge()));
	     element.appendChild(age);
	      Element role = getPropertyNode("role", document, employee.getRole());
	     element.appendChild(role);
	    return element;
	}
	
	private static Element getPropertyNode(String property, Document document, String value) {
	      Element element = document.createElement(property);
	      element.setTextContent(value);
	      return element;
	}
	
	public static String deleteEmployee(Document document, Integer id) throws TransformerException
	{
		updateNodeList(document);
		if (deleteEmployeeFromXml(document, id)) {
          TransformerFactory tFactory = TransformerFactory.newInstance();
          Transformer transformer = tFactory.newTransformer();
          transformer.setOutputProperty(OutputKeys.INDENT, "yes");
          DOMSource source = new DOMSource(document);
          StreamResult sResult = new StreamResult(document.getBaseURI());
          transformer.transform(source, sResult);
          return "Employee has been deleted successfully.";
       }
		return "Employee not exist.";
	}


	private static boolean deleteEmployeeFromXml(Document document, int id){
	    NodeList list = document.getElementsByTagName("Employee");
	    for (int i = 0; i < length; i++) {
	       Node node = list.item(i);
	       if (node.getNodeType() == Node.ELEMENT_NODE) {
	          Element element = (Element) node;
	          if (element.getAttribute("id").equals(String.valueOf(id))) {
	             Node prev = node.getPreviousSibling();
	             if (prev != null && prev.getNodeType() == Node.TEXT_NODE && prev.getNodeValue().trim().length() == 0) {
	                document.getDocumentElement().removeChild(prev);
	             }
	             document.getDocumentElement().removeChild(element);
	             return true;
	          }
	       }
	    }
	    return false;
	 }
}

