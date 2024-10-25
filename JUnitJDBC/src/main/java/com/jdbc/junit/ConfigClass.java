package com.jdbc.junit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConfigClass {
	private static Connection connection;
	private Statement statement;
	
	private static String driver = "org.postgresql.Driver";
	private static String username = "postgres";
	private static String password = "Emsobak321";
	private static String url = "jdbc:postgresql://localhost:5432/test_database";

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
