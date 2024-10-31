package webapp.srvlt.config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConfigClass {
	static String URL = "jdbc:postgresql://localhost:5432/servlets";
	static String username = "postgres";
	static String password = "Emsobak321";
	static String driver = "org.postgresql.Driver";

	public static Connection getConnection()  throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		return DriverManager.getConnection(URL, username, password);
	}

}
