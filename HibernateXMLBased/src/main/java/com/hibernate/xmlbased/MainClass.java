package com.hibernate.xmlbased;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hibernate.xmlbased.dao.DeveloperDAO;
import com.hibernate.xmlbased.model.Developer;

public class MainClass {	
	
	public static void main(String[] args) {
		List <Developer> developers = new ArrayList<>(Arrays.asList(
				new Developer("Igor", "Java Developer", 2),
				new Developer("Alexander", "C++ Developer", 4),
				new Developer("Ivan", "DevOps", 3)
				));
		
		DeveloperDAO developerDAO = new DeveloperDAO();
		
		// Check add operation
		developerDAO.addDeveloper(developers.get(0));
		developerDAO.addDeveloper(developers.get(1));
		developerDAO.addDeveloper(developers.get(2));
		
		// Print all devs
		developerDAO.getDevelopers().stream().forEach(System.out::println);
		
		// check getDeveloper
		System.out.println(developerDAO.getDeveloperById(2));
		
		//update devs
		developerDAO.updateDeveloper(1, 5);
		
		developerDAO.removeDeveloper(2);
		
		developerDAO.getDevelopers().stream().forEach(System.out::println);

	}
	
}
