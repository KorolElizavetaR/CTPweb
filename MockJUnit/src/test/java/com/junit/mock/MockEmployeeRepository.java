package com.junit.mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.junit.mock.exception.EmployeeNotFoundException;
import com.junit.mock.model.Employee;
import com.junit.mock.repository.EmployeeRepository;
import com.junit.mock.service.EmployeeService;

import jakarta.validation.ConstraintViolationException;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MockEmployeeRepository {
	@InjectMocks
	private EmployeeService service;
	@Mock
	private EmployeeRepository repository;
	
	private static Employee employee;
	private static Employee newEmployee;
	private static Employee invalidEmployee;
	
	@BeforeAll
	static void initialize()
	{
		employee = new Employee(5, "Валобуев Михаил Семенович", new Date(945153510963l), new BigDecimal("1000"));
		newEmployee = new Employee("Миськевич Константин Александрович", new Date(945153510963l), new BigDecimal("1200"));
		invalidEmployee = new Employee("Инвалиды! Огузки!", new Date(1022131568640l), new BigDecimal("-500"));
	}

	static Stream<Employee> OKempl() {
		return Stream.of(new Employee("Бут-Гусаим Денис Михалыч", new Date(945153510963l), new BigDecimal("1000")),
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
	void saveEmployee_Valid_Test(Employee empl) {
		doNothing().when(repository).save(empl);
		when(repository.findById(empl.getEmployeeId())).thenReturn(Optional.of(empl));
		Employee result = service.saveEmployee(empl);
		verify(repository).save(Mockito.any(Employee.class));
		verify(repository).findById(empl.getEmployeeId());
		assertEquals(empl, result);
	}

	@ParameterizedTest
	@MethodSource("BADempl")
	@Order (11)
	void saveEmployee_Invalid_Test(Employee empl) {
		assertThrows(ConstraintViolationException.class, () -> service.saveEmployee(empl));
	}
	
	@Test
	@Order (20)
	void removeEmployee_Successful_Test() {
		when(repository.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));
		doNothing().when(repository).delete(employee);
		service.removeEmployee(employee.getEmployeeId());
		verify(repository).delete(employee);
		verify(repository).findById(anyInt());
	}
	
	@Test
	@Order (30)
	void updateEmployee_Successful_Test() {
		when(repository.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));
		when(repository.update(newEmployee, employee.getEmployeeId())).thenReturn(Optional.of(newEmployee));
		Employee newEmp = service.updateEmployee(newEmployee, employee.getEmployeeId());
		assertEquals(newEmp, newEmployee);
		verify(repository).update(Mockito.any(Employee.class), anyInt());
		verify(repository).findById(anyInt());
	}
	
	@Test
	@Order (31)
	void updateEmployee_Invalid_Test() {
		assertThrows(ConstraintViolationException.class, () -> service.updateEmployee(invalidEmployee, 4));
	}
	

	@Test
	@Order (32)
	void updateEmployee_NotFound_Test() {
		when(repository.findById(employee.getEmployeeId())).thenReturn(Optional.empty());
		assertThrows(EmployeeNotFoundException.class, ()-> service.updateEmployee(newEmployee, employee.getEmployeeId()));
		verify(repository).findById(anyInt());
	}
	
}
