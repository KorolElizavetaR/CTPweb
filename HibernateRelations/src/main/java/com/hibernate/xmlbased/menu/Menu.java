package com.hibernate.xmlbased.menu;

import java.util.Scanner;

import com.hibernate.xmlbased.dao.DepartmentDAO;
import com.hibernate.xmlbased.dao.DeveloperDAO;
import com.hibernate.xmlbased.dao.UserDAO;
import com.hibernate.xmlbased.model.User;

public class Menu {
	User authUser;
	Scanner scanLine = new Scanner(System.in);
	Scanner scanInt = new Scanner(System.in);
	
	static DeveloperDAO developerDAO = new DeveloperDAO();
	static UserDAO userDAO = new UserDAO();
	static DepartmentDAO departmentDAO = new DepartmentDAO();
	
	public void menu(User authUser)
	{
		this.authUser = authUser;
		System.out.printf("\nHi, %s. Your role: %s\n", authUser.getDeveloper().getName(), authUser.getUserRole().getRole());
	}
}
