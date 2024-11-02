package com.webxml.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryWebXmlApplication {

	public static void main(String[] args) {
		System.out.println("localhost:8080/books");
		SpringApplication.run(LibraryWebXmlApplication.class, args);
	}

}
