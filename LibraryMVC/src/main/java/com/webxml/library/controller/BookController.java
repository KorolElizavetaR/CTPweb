package com.webxml.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping ("/books")
public class BookController {
	@Autowired
	private final PeopleService peopleService;
	@Autowired
	private final BookService bookService;
	
	// Получить список всех книг
	@GetMapping ()
	public String GetBooks(Model model, @RequestParam (value = "page", defaultValue = "0", required = true) String page, @RequestParam (value = "like", required = false) String like)
	{
		if (like != null && !(like.isBlank())) 
		{
			model.addAttribute("books", bookService.findBookByLikeStr(like));
			model.addAttribute("like", like);
		}
		else
		{
			model.addAttribute("books", bookService.findBookSortedTrue(Integer.valueOf(page)));
		}
		return "books/list";
	}
}
