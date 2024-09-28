package web.parser.sax.parser;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import web.parser.sax.model.*;

public class BooksParser extends DefaultHandler {
	Catalog myCatalog = null;
    Book book = null;

//    boolean author = false;
//    boolean title = false;
//    boolean description = false;
    String currentCharacters;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("catalog")) {
            myCatalog = new Catalog();
        } else if (qName.equalsIgnoreCase("book")) {
            book = new Book();
        } 
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("catalog")) {
            System.out.println(myCatalog);
        } else if (qName.equalsIgnoreCase("book")) {
            myCatalog.push(book);
        } else if (qName.equalsIgnoreCase("author")) {
            book.setAuthor(currentCharacters);
        } else if (qName.equalsIgnoreCase("title")) {
            book.setTitle(currentCharacters);
        } else if (qName.equalsIgnoreCase("description")) {
            book.setDescription(currentCharacters);
        }
    }

    @Override
    public void characters(char ch[], int start, int length ) {
        currentCharacters = new String(ch, start, length);
    }

}
