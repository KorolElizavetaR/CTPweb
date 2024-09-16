package web.jdbc.laba2.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component ("read")
public class ReadTables {
	@Autowired
	private JDBC jdbc;
	
	public void printTableAuthors() throws SQLException
	{
		 String query = "SELECT * FROM authors";
         System.out.println("authors:");
         ResultSet rs1 = jdbc.getStatement().executeQuery(query);
          while (rs1.next()) {
           int id = rs1.getInt("authorID");
            String firstName = rs1.getString("firstName");
            String lastName = rs1.getString("lastName");
            System.out.println(id + "\t" + firstName + "\t" + lastName);
          }
	}
	
	public void printTableAuthorisbn() throws SQLException
	{
		 String query = "SELECT * FROM authorisbn";
         System.out.println("authorisbn:");
         ResultSet rs1 = jdbc.getStatement().executeQuery(query);
          while (rs1.next()) 
            System.out.println(rs1.getInt("authorID") + "\t" + rs1.getString("isbn"));
	}
	
	public void printTableTitles() throws SQLException
	{
		 String query = "SELECT * FROM titles";
         System.out.println("titles:");
         ResultSet rs1 = jdbc.getStatement().executeQuery(query);
         while (rs1.next()) 
            System.out.println(rs1.getString("isbn") + "\t" 
            					+ rs1.getString("title") + "\t"
            					+ rs1.getInt("editionNumber") + "\t"
         						+ rs1.getString("year") + "\t"
         						+ rs1.getInt("publisherID") + "\t"
         						+ rs1.getDouble("price"));
	}
	
	public void printTablePublishers() throws SQLException
	{
		 String query = "SELECT * FROM publishers";
         System.out.println("publishers:");
         ResultSet rs1 = jdbc.getStatement().executeQuery(query);
         while (rs1.next()) 
            System.out.println(rs1.getInt("publisherID") + "\t" 
            					+ rs1.getString("publisherName"));
	}
}
