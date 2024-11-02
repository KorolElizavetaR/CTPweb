package com.webxml.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webxml.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	
}
