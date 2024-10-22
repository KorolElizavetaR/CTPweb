package com.hibernate.xmlbased.menu;

import java.util.List;
import java.util.Set;

import com.hibernate.xmlbased.dao.DepartmentDAO;
import com.hibernate.xmlbased.dao.DeveloperDAO;
import com.hibernate.xmlbased.dao.UserDAO;
import com.hibernate.xmlbased.model.Department;
import com.hibernate.xmlbased.model.Developer;
import com.hibernate.xmlbased.model.User;

public class AdminMenu extends Menu{
	
	public void menu(User authUser)
	{
		super.menu(authUser);
		Integer options = null;
		System.out.println("0. Exit");
		System.out.println("1. See departments and all workers there");
		System.out.println("2. Find department by ID");
		System.out.println("2. Add department");
		System.out.println("3. Update department");
		System.out.println("4. Delete department");
		System.out.println("5. Find developer by ID");// таска
		System.out.println("6. Add developer"); // таска, круд есть в классе
		System.out.println("7. Update developer"); // таска, круд есть в классе
		System.out.println("8. Delete developer"); // таска, круд есть в классе
		System.out.println("9. Find user by id"); // таска
		System.out.println("10. Add user"); // таска
		System.out.println("11. Delete user");// таска
		// Написать три SELECT-а с использованием HQL или Criteria: один для каждой таблицы
		while (true)
		{
			options = in.nextInt();
			switch(options)
			{
				case 0: return;
				case 1: seeAllWorkers(); break;
				default: System.out.println("Nope, not happening");
			}
		}
	}
	
	public void seeAllWorkers()
	{
		Set<Department> departments = departmentDAO.getDepartmentWithWorkers();
		for (Department department: departments)
		{
			System.out.println(department);
			List<Developer> devs = department.getDevelopers();
			for (Developer dev : devs)
			{
				System.out.println("\t" + dev);
			}
		}
	}
	
	
}
