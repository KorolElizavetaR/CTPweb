package com.ctpweb.laba4.service;

import org.springframework.stereotype.Service;

import com.ctpweb.laba4.dao.DeveloperDAO;
import com.ctpweb.laba4.model.Developer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeveloperService {
	private final DeveloperDAO developerDAO;
	
	public void printAllDevs()
	{
		developerDAO.getDevelopersList().stream().forEach(System.out::println);
	}
	
	public void addDev(Developer developer)
	{
		developerDAO.addDeveloper(developer);
		System.out.println("Dev " + developer + " is succesfully added");
	}
	
	public void editDev(Integer id, Developer dev) throws IllegalArgumentException, IllegalAccessException
	{
		developerDAO.updateDeveloper(id, dev);
		System.out.println("Dev " + dev + " is succesfully updated");
	}
	
	public void printDev(Integer id)
	{
		System.out.println(developerDAO.getDeveloper(id));
	}
	
	public void removeDev(Integer id)
	{
		developerDAO.removeDeveloper(id);
		System.out.println("Dev is succesfully removed");
	}

}
