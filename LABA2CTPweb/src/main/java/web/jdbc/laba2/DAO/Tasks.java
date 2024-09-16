package web.jdbc.laba2.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component ("tasks")
public class Tasks {
	@Autowired
	private JDBC jdbc;
	
	@Autowired
	private ReadTables read;
	
	// Сделайте выборку по авторам, отсортировав по их Имени и Фамилии
	public void task1() throws SQLException
	{
		String query = "SELECT * FROM authors ORDER BY \"firstName\", \"lastName\"";
        ResultSet rs1 = jdbc.getStatement().executeQuery(query);
         while (rs1.next()) {
          int id = rs1.getInt("authorID");
           String firstName = rs1.getString("firstName");
           String lastName = rs1.getString("lastName");
           System.out.println(id + "\t" + firstName + "\t" + lastName);
         }
	}
	
	// Добавьте нового Издателя (publusher).
	public void task2() throws SQLException 
	{
		 jdbc.getStatement().executeUpdate("INSERT INTO publishers(\"publisherName\") VALUES ('Amongus')");
	}
	
	//Сделайте выборку Издателей и измените имя определенного Издателя.
	public void task3() throws SQLException 
	{
		String query = "SELECT * FROM publishers WHERE \"publisherID\" = 10";
		 ResultSet rs1 = jdbc.getStatement().executeQuery(query);
		 rs1.next();
		 System.out.println(rs1.getInt("publisherID") + "\t" + rs1.getString("publisherName"));
		 jdbc.getStatement().executeUpdate("UPDATE publishers SET \"publisherName\" = 'Valley' WHERE \"publisherID\" = 10");
		 rs1 = jdbc.getStatement().executeQuery(query);
		 rs1.next();
		 System.out.println(rs1.getInt("publisherID") + "\t" + rs1.getString("publisherName"));
	}
	
	// Предоставьте отсортированный список книг определенного издателя (при этом id требуемого издателя можно менять в sql запросе)
	public void task4() throws SQLException 
	{
		String query = "SELECT * FROM titles WHERE \"publisherID\" = 1 ORDER BY title";
		 ResultSet rs1 = jdbc.getStatement().executeQuery(query);
		 while (rs1.next())
		 { 
			 System.out.println(rs1.getString("isbn") + "\t" 
					+ rs1.getString("title") + "\t"
					+ rs1.getInt("editionNumber") + "\t"
					+ rs1.getString("year") + "\t"
					+ rs1.getInt("publisherID") + "\t"
					+ rs1.getDouble("price"));
		 }
	}
	
	// Выполните добавление Нового автора в БД
	public void task5() throws SQLException
	{
		 jdbc.getStatement().executeUpdate("INSERT INTO authors(\"firstName\", \"lastName\") VALUES ('Yevgeny', 'Zamyatin')");
	}
	
	// Обновите Имя автора по определенному id
	public void task6() throws SQLException
	{
		 jdbc.getStatement().executeUpdate("UPDATE authors SET \"firstName\" = 'Joanne', \"lastName\"='Rowling' WHERE \"authorID\"=11");
	}
	
	// там длинное условие
	public void task7() throws SQLException
	{
		jdbc.getStatement().executeUpdate("INSERT INTO publishers(\"publisherName\") VALUES ('Amongus')");
		jdbc.getStatement().executeUpdate("INSERT INTO titles(isbn, title, \"editionNumber\", year, \"publisherID\", price) VALUES\r\n"
		 		+ "	('5343175416', 'Demons', 1, 1888, (select \"publisherID\" from publishers where \"publisherName\" = 'Amongus'), 8.82);");
		jdbc.getStatement().executeUpdate("INSERT INTO authorisbn VALUES ((SELECT \"authorID\" "
				+ "FROM authors WHERE \"firstName\" = 'Fyodor' AND \"lastName\" = 'Dostoevsky'), '5343175416');");
	}
}
