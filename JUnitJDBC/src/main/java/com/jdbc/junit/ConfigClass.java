package com.jdbc.junit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConfigClass {
	private static Connection connection;
	private Statement statement;
	
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String username = "root";
	private static String password = "ЗМЩлшкпшы789";
	private static String url = "jdbc:mysql://localhost:3306/testdb";

	public ConfigClass() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		connection = DriverManager.getConnection(url, username, password);
		statement = connection.createStatement();
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	public void closeConnection() throws SQLException 
	{
		 connection.close();
	}
}
