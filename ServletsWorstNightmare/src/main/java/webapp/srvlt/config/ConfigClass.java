package webapp.srvlt.config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigClass {
	static String URL = "jdbc:postgresql://localhost:5432/servlets";
	static String username = "postgres";
	static String password = "Emsobak321";
	static String driver = "org.postgresql.Driver";
	
	private static final Logger logger = LogManager.getLogger(ConfigClass.class);

	public static Connection getConnection()  throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		logger.info("connection establish");
		return DriverManager.getConnection(URL, username, password);
	}

}
