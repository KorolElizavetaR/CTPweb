package com.hibernate.xmlbased.menu;

import com.hibernate.xmlbased.dao.DepartmentDAO;
import com.hibernate.xmlbased.dao.DeveloperDAO;
import com.hibernate.xmlbased.dao.UserDAO;
import com.hibernate.xmlbased.model.User;

public class AdminMenu extends Menu{
	public void menu(User authUser)
	{
		super.menu(authUser);
		Integer options = null;
		System.out.println("0. Exit");
		System.out.println("1. See departments and all workers there");
		System.out.println("2. Add department"); // таска
		System.out.println("3. Add developer");
		System.out.println("4. Update developer");
		System.out.println("5. Delete developer");
		System.out.println("6. Add user");
		System.out.println("7. Delete user");
		// Написать три SELECT-а с использованием HQL или Criteria: один для каждой таблицы
		while (true)
		{
			options = in.nextInt();
			switch(options)
			{
				case 0:
			}
		}
	}
}
