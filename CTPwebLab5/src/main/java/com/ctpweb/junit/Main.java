package com.ctpweb.junit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.ctpweb.junit.model.Programmer;
import com.ctpweb.junit.service.ProgrammerService;

@SpringBootApplication
public class Main {
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Main.class, args);
		ProgrammerService service = context.getBean(ProgrammerService.class);
		service.addProgrammer(Programmer.builder().
				name("Киселева Татьяна Сергеевна").
				location("г. Минск, Орловского 44").
				build());
	}

}
