package com.junit.mock.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private int employeeId;

	@Column(name = "empl_name", nullable = false, length = 30)
	@Pattern (regexp = "[А-ЯЁ][-А-яЁё]+ [А-ЯЁ][-А-яЁё]+ [А-ЯЁ][-А-яЁё]+", message = "Некорректный ввод ФИО")
	@NotEmpty (message = "Имя не может быть пустым")
	private String emplName;

	@Column(name = "empl_dob", nullable = false)
	@Temporal(TemporalType.DATE)
	@Past (message = "Время не может быть в будущем")
	@NotNull (message = "Дата рождения не может быть пустой")
	private Date emplDob;

	@Column(name = "empl_salary", precision = 10, scale = 2)
	@Positive (message = "Зарплата должна быть положительной")
	private BigDecimal emplSalary;

	public Employee() {
	}

	public Employee(String emplName, Date emplDob, BigDecimal emplSalary) {
		this.emplName = emplName;
		this.emplDob = emplDob;
		this.emplSalary = emplSalary;
	}

	public Employee(int employeeId, String emplName, Date emplDob, BigDecimal emplSalary) {
		this(emplName, emplDob, emplSalary);
		this.employeeId = employeeId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public Employee setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
		return this;
	}

	public String getEmplName() {
		return emplName;
	}

	public Employee setEmplName(String emplName) {
		this.emplName = emplName;
		return this;
	}

	public Date getEmplDob() {
		return emplDob;
	}

	public Employee setEmplDob(Date emplDob) {
		this.emplDob = emplDob;
		return this;
	}

	public BigDecimal getEmplSalary() {
		return emplSalary;
	}

	public Employee setEmplSalary(BigDecimal emplSalary) {
		this.emplSalary = emplSalary;
		return this;
	}

	@Override
	public String toString() {
		return "Employee{" + "employeeId=" + employeeId + ", emplName='" + emplName + '\'' + ", emplDob=" + emplDob
				+ ", emplSalary=" + emplSalary + '}';
	}

	@Override
	public int hashCode() {
		return Objects.hash(emplDob, emplName, emplSalary, employeeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(emplDob, other.emplDob) && Objects.equals(emplName, other.emplName)
				&& Objects.equals(emplSalary, other.emplSalary) && employeeId == other.employeeId;
	}

}
