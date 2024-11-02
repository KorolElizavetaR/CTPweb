package com.junit.mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.junit.mock.dao.EmployeeService;
import com.junit.mock.model.Employee;
import com.junit.mock.service.EmployeeRepository;

import jakarta.validation.ConstraintViolationException;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MockTests {
	@InjectMocks
	private EmployeeService employeeDAO;
	@Mock
	private EmployeeRepository service;

	static Stream<Employee> OKempl() {
		return Stream.of(new Employee("Батроев Михаил Михалыч", new Date(945153510963l), new BigDecimal("1000")),
				new Employee("Терентьев Михал Палыч", new Date(988349168640l), new BigDecimal("1500")),
				new Employee("Фролов Алексей Васильевич", new Date(1022131568640l), new BigDecimal("1000")));
	}

	static Stream<Employee> BADempl() {
		return Stream.of(new Employee("InvalidName", new Date(945153510963l), new BigDecimal("1000")),
				new Employee("Терентьев Михал Палыч", new Date(1734158310963l), new BigDecimal("1500")),
				new Employee("Фролов Алексей Васильевич", new Date(1022131568640l), new BigDecimal("-500")));
	}

	@ParameterizedTest
	@MethodSource("OKempl")
	@Order(10)
	void addValidEmployee_Test(Employee empl) {
		doNothing().when(service).save(empl);
		when(service.findById(empl.getEmployeeId())).thenReturn(Optional.of(empl));
		Employee result = employeeDAO.saveEmployee(empl);
		verify(service).save(empl);
		verify(service).findById(empl.getEmployeeId());
		assertEquals(empl, result);
	}

	@ParameterizedTest
	@MethodSource("BADempl")
	@Order (11)
	void addInvalidEmployee_Test(Employee empl) {
		assertThrows(ConstraintViolationException.class, () -> employeeDAO.saveEmployee(empl));
	}
}
