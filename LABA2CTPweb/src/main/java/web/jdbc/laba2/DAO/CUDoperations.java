package web.jdbc.laba2.DAO;

import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component ("cudoperations")
public class CUDoperations {
	@Autowired
	private JDBC jdbc;

	public void dropTables() throws SQLException
	{
		Statement statement = jdbc.getStatement();
		statement.executeUpdate("DROP TABLE IF EXISTS authorsisbn");
		statement.executeUpdate("DROP TABLE IF EXISTS authors");
		statement.executeUpdate("DROP TABLE IF EXISTS titles");
		statement.executeUpdate("DROP TABLE IF EXISTS publishers");
	}
	
	public void createTables() throws SQLException
	{
		Statement statement = jdbc.getStatement();
		String authorsTable = "CREATE TABLE authors " +
                "(\"authorID\" int PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                " \"firstName\" varchar(20), " +
                " \"lastName\" varchar(20))";
		statement.executeUpdate(authorsTable);
		
		 String publishersTable = "CREATE TABLE publishers " +
	                "(\"publisherID\" int PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
	                " \"publisherName\" varchar(100)) ";
		 statement.executeUpdate(publishersTable);

		 String titlesTable = "CREATE TABLE titles " +
                "(isbn varchar(13) PRIMARY KEY, " +
                " title varchar(255), " +
                " \"editionNumber\" int, " +
                " year varchar(4), " +
                " \"publisherID\" int REFERENCES publishers(\"publisherID\"), " +
                " price DECIMAL(8,2))";
		 statement.executeUpdate(titlesTable);

		 String authorISBNTable = "CREATE TABLE authorisbn " +
                "(\"authorID\" int REFERENCES authors(\"authorID\"), " +
                " isbn varchar(10) REFERENCES titles(isbn))";

		 statement.executeUpdate(authorISBNTable);
	}
	
	private void updateAuthorsTable() throws SQLException {
		Statement statement = jdbc.getStatement();
	      String authorFirstNames[] = { "Jane", "Dan", "Ralph Waldo", "F.Scott","John", "Ernest", "Walter", "Stephen", "Stieg",
	            "George", "Joanne K.", "John", "John R. R.", "Kurt", "Andy" };
	      String authorLastNames[] = { "Austen", "Brown", "Emerson", "Firtzgerald", "Grisham", "Hemingway", "Isaacson",
	            "King", "Larsson", "Orwell", "Rowling", "Steinbeck", "Tolkien", "Vonnegut", "Weir" };
	      
	      for (int i = 0; i < authorFirstNames.length; i++) {
	    	  statement.executeUpdate("INSERT INTO authors (\"firstName\", \"lastName\")" + "VALUES ('" + authorFirstNames[i]
	 	               + "', '" + authorLastNames[i] + "');");
	      }
	   }

	   private void updateAuthorIsbnTable() throws SQLException {
		   Statement statement = jdbc.getStatement();
	      String isbn[] = { "0141439519", "0307474278", "0142437629", "0743273565", "0345543240", "0684801223",
	            "1501127625", "1501175466", "0307949486", "0451524935", "0439708180", "0142000687", "0547928227",
	            "0385333849", "0553418026" };
	      for (int i = 1; i <= isbn.length; i++) {
	         String updateAuthorISBNTable = "INSERT INTO authorISBN (\"authorID\", isbn)" + "VALUES (" + i + ", '"+ isbn[i-1] + "')";
	         statement.executeUpdate(updateAuthorISBNTable);
	      }
	   }

	   private void updateTitlesTable() throws SQLException {
		   Statement statement = jdbc.getStatement();
	      String isbn[] = { "0141439519", "0307474278", "0142437629", "0743273565", "0345543240", "0684801223",
	            "1501127625", "1501175466", "0307949486", "0451524935", "0439708180", "0142000687", "0547928227",
	            "0385333849", "0553418026" };
	      String editionNumber[] = { "10", "10", "8", "4", "5", "7", "3", "14", "5", "3", "1", "18", "12", "2", "3" };
	      String year[] = { "2002", "2009", "2003", "2004", "2014", "1995", "2015", "2017", "2011", "1961", "1999",
	            "2002", "2012", "1999", "2014" };
	      String publisherID[] = { "1", "2", "1", "3", "4", "3", "5", "3", "6", "7", "8", "1", "9", "10", "11" };
	      String price[] = { "8.00", "8.99", "13.58", "6.99", "8.49", "9.98", "11.99", "9.55", "8.60", "7.64", "7.47",
	            "13.60", "11.16", "12.51", "10.76" };
	      String title[] = { "Pride and Prejudice", "The Da Vinci Code", "Nature and Selected Essays", "The Great Gatsby",
	            "Sycamore Row", "The Old Man and The Sea", "Steve Jobs", "It: A Novel",
	            "The Girl with the Dragon Tattoo", "1984", "Harry Potter and the Sorcerer''s Stone", "Cannery Row",
	            "The Hobbit", "Slaughterhouse-Five", "The Martian" };

	      for (int i = 0; i < isbn.length; i++) {
	         String updateTitlesTable = "INSERT INTO titles (isbn, title, \"editionNumber\", year, \"publisherID\", price)"
	               + "VALUES ('" + isbn[i] + "', '" + title[i] + "', " + editionNumber[i] + ",'" + year[i] + "',"
	               + publisherID[i] + "," + price[i] + ")";
	         statement.executeUpdate(updateTitlesTable);
	      }
	   }

	   private void updatePublishersTable() throws SQLException {
		   Statement statement = jdbc.getStatement();
	      String publishers[] = { "Penguin", "Anchor", "Scribner", "Dell Books", "Simon & Schuster", "Vintage Crime",
	            "Signet", "Scholastic", "Houghton Mifflin", "Dial Press", "Broadway" };
	      for (int i = 0; i < publishers.length; i++) {
	    	  statement.executeUpdate("INSERT INTO publishers (\"publisherName\")" + "VALUES ('" + publishers[i] + "')");
	      }
	   }
	   
	   public void insertIntoTable() throws SQLException
	   {
		   updateAuthorsTable();
		   updatePublishersTable();
		   updateTitlesTable();
		   updateAuthorIsbnTable();
	   }
}
