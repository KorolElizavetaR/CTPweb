package com.hibernate.xmlbased;

import java.util.Scanner;

import com.hibernate.xmlbased.dao.DepartmentDAO;
import com.hibernate.xmlbased.dao.DeveloperDAO;
import com.hibernate.xmlbased.dao.UserDAO;
import com.hibernate.xmlbased.menu.AdminMenu;
import com.hibernate.xmlbased.menu.Menu;
import com.hibernate.xmlbased.menu.UserMenu;
import com.hibernate.xmlbased.model.User;

import jakarta.persistence.NoResultException;

public class MainClass {	
	static UserDAO userDAO = new UserDAO();
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Menu menu = null;
		while (true)
		{
			System.out.println("Username:");
			String username = in.nextLine();
			System.out.println("Password:");
			String password = in.nextLine();
			User user;
			try
			{
				user = userDAO.AuthUser(username, password);
			}
			catch(NoResultException ex)
			{
				System.out.println("Bad credentials");
				continue;
			}
			switch(user.getUserRole())
			{
			case ROLE_ADMIN:
				menu = new AdminMenu();
				break;
			case ROLE_USER:
				menu = new UserMenu();
				break;
			}
			menu.menu(user);
			break;
		}
	}
	
}
