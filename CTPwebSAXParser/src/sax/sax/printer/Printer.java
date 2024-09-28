package sax.printer;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.events.StartElement;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Printer {
	private static Printer employeePrinter;
	
	public static Printer getInstanceOfEmployeePrinter() throws SAXException, IOException, ParserConfigurationException
	{
		if (employeePrinter == null)
			return new EmployeePrinter(new EmployeeService(), buildDocument());
		return employeePrinter;
	}
	
	private static Document buildDocument() throws SAXException, IOException, ParserConfigurationException
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		parser.parse(new File("src\\resources\\books.xml"), new DefaultHandler()
				{
				@Override
				public void startDocument() throws SAXException {
					
				};
				
				}
				);
		return document;
	}

}
