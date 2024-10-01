package com.ctpweb.laba4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.ctpweb.laba4.service.DeveloperService;

@SpringBootApplication
public class Laba4Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Laba4Application.class, args);
		DeveloperService service = context.getBean(DeveloperService.class);
//		service.addDev(new Developer("Maria", "Kain", "Python-dev", 2, 1200));
//		service.addDev(new Developer("Victorya", "Olgymskaya", "DevOps", 4, 1500));
//		service.addDev(new Developer("Michail", "Baranov", "DevOps", 1, 500));
//		service.addDev(new Developer("Victorya", "Frolova", "System Analytics", 2, 1400));
//		service.addDev(new Developer("Sergey", "Nemchinsky", "Java Backend dev", 10, 15000));
//		service.printAllDevs();
//		service.printDevsWithSalaryNotLessThan(1400);
//		service.totalSalaryOfAllWorkers();
	}

}
