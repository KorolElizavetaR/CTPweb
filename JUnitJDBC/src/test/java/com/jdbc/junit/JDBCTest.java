package com.jdbc.junit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class JDBCTest {
	static ConfigClass config;
	static CRUD database;

	static Stream<Employee> OKempl() {
		return Stream.of(new Employee("Vasya", new Date(945153510963l), new BigDecimal("1000")),
				new Employee("Maksim", new Date(988349168640l), new BigDecimal("1500")),
				new Employee("Vova", new Date(1022131568640l), new BigDecimal("1000")));
	}

	static Stream<Employee> BADempl() {
		return Stream.of(new Employee("Vasya", new Date(1734158310963l), new BigDecimal("1000")), // wrong date
				new Employee("", new Date(988349168640l), new BigDecimal("1500")), // wrong name
				new Employee("Vova", new Date(1022131568640l), new BigDecimal("-3"))); // salary below 0
	}

	@BeforeAll
	static void createConfigInstance() throws ClassNotFoundException, SQLException {
		config = new ConfigClass();
		database = new CRUD();
	}

	@AfterAll
	static void closeContext() {
		try {
			config.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@ParameterizedTest
	@MethodSource("OKempl")
	void testCreateSuccesful(Employee employee) {
		try {
			database.createEmployee(employee);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("SQLException");
		}
	}

	@ParameterizedTest
	@MethodSource("BADempl")
	void testCreateSQLexception(Employee employee) {
		assertThrows(SQLException.class, () -> database.createEmployee(employee));
	}

	@Test
	void testDelete() {
		try {
			database.deleteEmployee(1);
		} catch (SQLException e) {
			fail("Employee is not found");
		}
	}

	@Test
	void testRead() {
		try {
			List<Employee> employees = database.readTable();
			assertNotNull(employees);
			assertNotEquals(employees.size(), 0);
		} catch (SQLException e) {
			fail("SQLException");
		}
	}
}
