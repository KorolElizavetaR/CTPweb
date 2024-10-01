package com.ctpweb.laba4;

import com.ctpweb.laba4.dao.DeveloperDAO;
import com.ctpweb.laba4.model.Developer;
import com.ctpweb.laba4.service.DeveloperService;

public class Laba4Application {

	public static void main(String[] args) {
		try {
			DeveloperService service = new DeveloperService(new DeveloperDAO());
			service.printAllDevs();
			// service.editDev(2, new Developer("Viktoria", "Olgymskaya", "HR", 3));
			//service.addDev(new Developer("Veronika", "Mak", "DevOps", 5));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
