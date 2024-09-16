package web.jdbc.laba2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PreDestroy;
import lombok.Getter;

@Component ("conn")
public class ConnectionFactory {
	@Value ("${spring.datasource.url}") String URL;
	@Value ("${spring.datasource.username}") String username;
	@Value ("${spring.datasource.password}") String password;
	@Value ("${spring.datasource.driver-class-name}") String driver;
	
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		return DriverManager.getConnection(URL, username, password);
	}

}
