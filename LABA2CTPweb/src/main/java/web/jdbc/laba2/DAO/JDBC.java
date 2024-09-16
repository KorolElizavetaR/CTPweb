package web.jdbc.laba2.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PreDestroy;
import lombok.Getter;

@Component ("jdbc")
public class JDBC {
	   private static Connection connection;
	   @Getter
	   private Statement statement;
	   
	   public JDBC(@Value ("${spring.datasource.url}") String URL,
		@Value ("${spring.datasource.username}") String username,
		@Value ("${spring.datasource.password}") String password,
		@Value ("${spring.datasource.driver-class-name}") String driver) throws ClassNotFoundException, SQLException
	   {
		   Class.forName(driver);
			connection = DriverManager.getConnection(URL, username, password);
			statement = connection.createStatement();
	   }
		
	  @PreDestroy
	  public void close() throws SQLException {
		   connection.close();
	  }

}
