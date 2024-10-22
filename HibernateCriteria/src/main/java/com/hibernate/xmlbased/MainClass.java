package com.hibernate.xmlbased;

import java.util.ArrayList;
import java.util.Arrays;

import com.hibernate.xmlbased.dao.DeveloperDAO;

public class MainClass {	
	public static void main(String[] args) {
		DeveloperDAO developerDAO = new DeveloperDAO();
		developerDAO.findAllDevs().stream().forEach(System.out::println);
		System.out.println(developerDAO.findByName("Ivan"));
		developerDAO.findByNameLike("ya").stream().forEach(System.out::println);
		developerDAO.findByExperienceGreaterThanAndSpecialtyIn(1, new ArrayList<>(Arrays.asList("C++ Developer", "Java Developer"))).
		stream().forEach(System.out::println);
		developerDAO.deleteDevByName("Ivan");
		developerDAO.findAllDevs().stream().forEach(System.out::println);
	}
}
