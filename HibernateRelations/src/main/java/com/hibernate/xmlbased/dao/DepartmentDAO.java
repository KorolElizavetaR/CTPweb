package com.hibernate.xmlbased.dao;

import com.hibernate.xmlbased.config.SessionConfig;

public class DepartmentDAO {
	private SessionConfig sc;
	
	public DepartmentDAO() {
		sc = SessionConfig.getInstanceOfSeccionFactory();
	}
}
