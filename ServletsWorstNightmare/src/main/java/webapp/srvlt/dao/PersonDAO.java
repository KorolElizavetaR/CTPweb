package webapp.srvlt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import webapp.srvlt.config.ConfigClass;
import webapp.srvlt.model.Person;

public class PersonDAO {

	private final static String SQL_GET_PERSONS = "SELECT * FROM people";
	private final static String SQL_INSERT_PERSONS = "INSERT INTO people(pname, phone, email) VALUES (?,?,?)";

	public void insertPerson(Person person) throws ClassNotFoundException, SQLException {
		Connection connection = ConfigClass.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_PERSONS);
			preparedStatement.setString(1, person.getName());
			preparedStatement.setString(2, person.getPhone());
			preparedStatement.setString(3, person.getEmail());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Person> getPeople() throws ClassNotFoundException, SQLException {
		Connection connection = ConfigClass.getConnection();
		List<Person> persons = new LinkedList<Person>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_GET_PERSONS);

			Person person = null;
			while (resultSet.next()) {
				person = new Person();

				person.setName(resultSet.getString("pname"));
				person.setPhone(resultSet.getString("phone"));
				person.setEmail(resultSet.getString("email"));

				persons.add(person);
			}
			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persons;
	}

}
