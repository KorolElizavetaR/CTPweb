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
		System.out.println("2. See my pals");
		System.out.println("3. Change my password");
			while (true)
			{
				try
				{
				options = in.nextInt();
				}
				catch (InputMismatchException e) {
					System.out.println("Is not an instance of Integer");
				}
				switch(options)
				{
					case 0: return;
					case 1: break;
					case 2: break;
					case 3: updatePassword();break;
					default: System.out.println("Не надо, пожалуйста.");
				}
			}
	}
	
	public void updatePassword()
	{
		System.out.print("Input new password: ");
		String password = in.nextLine();
		try
		{
			super.userDAO.updatePassword(super.authUser.getUsername(), password);
		}
		catch (Exception ex)
		{
			System.out.println("Some error occured: " + ex.getMessage());
			return;
		}
		
	}
}
