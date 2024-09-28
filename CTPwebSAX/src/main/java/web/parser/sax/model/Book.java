package web.parser.sax.model;

import lombok.Data;

@Data
public class Book {
	 private String id;
	 private String author;
	 private String title;
	 private String genre;
	 private Double price;
	 private String publishDate;
	 private String description;
}
