package com.hibernate.xmlbased.menu;

import com.hibernate.xmlbased.model.User;

public class UserMenu extends Menu {

	public void menu(User authUser) {
		super.menu(authUser);
		Integer options = null;
		System.out.println("0. Выход");
		System.out.println("1. Информация по моему департаменту");
		System.out.println("2. Получить список моих коллег");
		System.out.println("3. Сменить мой пароль");
		while (true) {
			try {
				System.out.print("Ввод: ");
				options = in.nextInt();
				switch (options) {
				case 0:
					return;
				case 1:
					getMyDepartment(authUser);
					break;
				case 2:
					getMyPals(authUser);
					break;
				case 3:
					updatePassword(authUser);
					break;
				default:
					System.out.println("Не надо, пожалуйста.");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void updatePassword(User user) {
		System.out.print("Input new password: ");
		String asdawd = in.nextLine(); // заглушка
		String password = in.nextLine();
		try {
			userDAO.updatePassword(user.getUserId(), password);
		} catch (Exception ex) {
			System.out.println("Some error occured: " + ex.getMessage());
			return;
		}
	}

	// азазазз затраллил лолку, заскамил, тут не нужно юзать бд :D
	// Потом расскажу что это за магия вне Хогвартса
	// таска
	public void getMyDepartment(User user) {
		System.out.println("Your department: " + user.getDeveloper().getDepartment());
	}

	public void getMyPals(User user) {
		departmentDAO.getDevelopersByDepartment(user.getDeveloper().getDepartment()).stream()
				.forEach(System.out::println);
	}
}
