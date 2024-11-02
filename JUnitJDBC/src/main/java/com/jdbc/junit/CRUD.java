package com.jdbc.junit;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CRUD {

	public void createEmployee(String empl_name, Date empl_dob, Integer empl_salary) throws SQLException {
		String query = "INSERT INTO employee (empl_name, empl_dob, empl_salary) VALUES (?,?,?);";
		PreparedStatement statement = ConfigClass.getConnection().prepareStatement(query);
		statement.setString(1, empl_name);
		statement.setDate(2, empl_dob);
		statement.setInt(3, empl_salary);
		statement.executeUpdate();
	}

	public Integer createEmployee(Employee empl) throws SQLException {
		String query = "INSERT INTO employee (empl_name, empl_dob, empl_salary) VALUES (?,?,?);";
		PreparedStatement statement = ConfigClass.getConnection().prepareStatement(query);
		statement.setString(1, empl.getEmplName());
		statement.setDate(2, empl.getEmplDob());
		statement.setBigDecimal(3, empl.getEmplSalary());
		int id = statement.executeUpdate();
		return id;
	}

	public void deleteEmployee(Integer id) throws SQLException {
		String query = "DELETE FROM employee WHERE employee_id=?;";
		PreparedStatement pstmt = ConfigClass.getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
	}

	public List<Employee> readTable() throws SQLException {
		List<Employee> employees = new ArrayList<>();
		Statement stmt = ConfigClass.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
		while (rs.next()) {
			int employeeId = rs.getInt("employee_id");
			String emplName = rs.getString("empl_name");
			Date emplDob = rs.getDate("empl_dob");
			BigDecimal emplSalary = rs.getBigDecimal("empl_salary");
			Employee employee = new Employee(employeeId, emplName, emplDob, emplSalary);
			employees.add(employee);
		}
		return employees;
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
