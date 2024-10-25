package com.jdbc.junit;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUD {

	public void createEmployee(String empl_name, Date empl_dob, Integer empl_salary) throws SQLException {
		String query = "INSERT INTO employee (empl_name, empl_dob, empl_salary) VALUES (?,?,?);";
		PreparedStatement statement = ConfigClass.getConnection().prepareStatement(query);
		statement.setString(1, empl_name);
		statement.setDate(2, empl_dob);
		statement.setInt(3, empl_salary);
		statement.executeUpdate();
	}
	
	public void createEmployee(Employee empl) throws SQLException {
		String query = "INSERT INTO employee (empl_name, empl_dob, empl_salary) VALUES (?,?,?);";
		PreparedStatement statement = ConfigClass.getConnection().prepareStatement(query);
		statement.setString(1, empl.getEmplName());
		statement.setDate(2, empl.getEmplDob());
		statement.setBigDecimal(3, empl.getEmplSalary());
		statement.executeUpdate();
	}

	public void deleteEmployee(Integer id) throws SQLException {
		String query = "DELETE FROM employee WHERE employee_id=?;";
		PreparedStatement pstmt = ConfigClass.getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
	}

	public ResultSet readTable() throws SQLException {
		Statement stmt = ConfigClass.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
		return rs;
	}

	public void updateEmployee(Integer id, String empl_name, Date empl_dob, Integer empl_salary) throws SQLException {
		String query = "UPDATE employee SET empl_name=?, empl_dob=?, empl_salary=? WHERE employee_id=?;";
		PreparedStatement statement = ConfigClass.getConnection().prepareStatement(query);
		statement.setString(1, empl_name);
		statement.setDate(2, empl_dob);
		statement.setInt(3, empl_salary);
		statement.setInt(4, id);
		statement.executeUpdate();
	}

}
