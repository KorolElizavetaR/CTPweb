package com.ctpweb.laba4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.ctpweb.laba4.dao.DeveloperDAO;
import com.ctpweb.laba4.model.Developer;
import com.ctpweb.laba4.service.DeveloperService;

@SpringBootApplication
public class Laba4Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Laba4Application.class, args);
		DeveloperService service = context.getBean(DeveloperService.class);
		service.addDev(new Developer("Maria", "Kain", "Python-dev", 2));
		service.printAllDevs();
		service.removeDev(6);
	}

}
