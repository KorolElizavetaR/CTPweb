package com.hibernate.xmlbased.menu;

import java.util.InputMismatchException;

import com.hibernate.xmlbased.dao.DepartmentDAO;
import com.hibernate.xmlbased.dao.DeveloperDAO;
import com.hibernate.xmlbased.dao.UserDAO;
import com.hibernate.xmlbased.model.User;

public class UserMenu extends Menu{
	
	public void menu(User authUser)
	{
		super.menu(authUser);
		Integer options = null;
		System.out.println("0. Exit");
		System.out.println("1. See my department");
		System.out.println("2. Get employees of my department");
		System.out.println("3. Change my password");
			while (true)
			{
				try
				{
					System.out.print("Option: ");
				options = in.nextInt();
				}
				catch (InputMismatchException e) {
					System.out.println("Is not an instance of Integer");
				}
				switch(options)
				{
					case 0: return;
					case 1: getMyDepartment (authUser); break;
					case 2: getMyPals(authUser); break;
					case 3: updatePassword(authUser); break;
					default: System.out.println("Не надо, пожалуйста.");
				}
			}
	}
	
	public void updatePassword(User user)
	{
		System.out.print("Input new password: ");
		String asdawd = in.nextLine();
		String password = in.nextLine();
		try
		{
			super.userDAO.updatePassword(user.getUserId(), password);
		}
		catch (Exception ex)
		{
			System.out.println("Some error occured: " + ex.getMessage());
			return;
		}
	}
	
	// азазазз затраллил лолку, заскамил, тут не нужно юзать бд :D
	// Потом расскажу что это за магия вне Хогвартса
	// таска
	public void getMyDepartment(User user)
	{
		System.out.println("Your department: " + user.getDeveloper().getDepartment());
	}
	
	public void getMyPals(User user)
	{
		departmentDAO.getDevelopersByDepartment(user.getDeveloper().getDepartment()).stream().forEach(System.out::println);
	}
}
