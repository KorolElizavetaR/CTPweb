package web.parser.sax;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import web.parser.sax.parser.BooksParser;

public class Main {
	public static void main(String[] args) {
		SAXParser parser;
		try {
			parser = SAXParserFactory.newInstance().newSAXParser();
			parser.parse(new File("src\\main\\resources\\books.xml"), new BooksParser());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}
