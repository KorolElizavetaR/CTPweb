package com.hibernate.xmlbased;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hibernate.xmlbased.dao.DeveloperDAO;
import com.hibernate.xmlbased.model.Developer;

public class MainClass {	
	
	public static void main(String[] args) {
		List <Developer> developers = new ArrayList<>(Arrays.asList(
				new Developer("Anya", "Java Developer", 3),
				new Developer("Olya", "C++ Developer", 1)
				));
		
		DeveloperDAO developerDAO = new DeveloperDAO();
		
		// Check add operation
		developerDAO.addDeveloper(developers.get(0));
		developerDAO.addDeveloper(developers.get(1));
		
		// Print all devs
		developerDAO.getDevelopers().stream().forEach(System.out::println);
		
		// check getDeveloper
		System.out.println(developerDAO.getDeveloperById(4));
		
		//update devs
		developerDAO.updateDeveloper(1, 1);
		
		developerDAO.removeDeveloper(1);
		
		developerDAO.getDevelopers().stream().forEach(System.out::println);

	}
	
}
