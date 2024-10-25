package com.jdbc.junit;

import java.math.BigDecimal;
import java.sql.Date;

public class Employee {

    private int employeeId;
    private String emplName;
    private Date emplDob;
    private BigDecimal emplSalary;

    public Employee() {
    }

    public Employee(String emplName, Date emplDob, BigDecimal emplSalary) {
		this.emplName = emplName;
		this.emplDob = emplDob;
		this.emplSalary = emplSalary;
	}

    public Employee(int employeeId, String emplName, Date emplDob, BigDecimal emplSalary) {
        this.employeeId = employeeId;
        this.emplName = emplName;
        this.emplDob = emplDob;
        this.emplSalary = emplSalary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmplName() {
        return emplName;
    }

    public void setEmplName(String emplName) {
        this.emplName = emplName;
    }

    public Date getEmplDob() {
        return emplDob;
    }

    public void setEmplDob(Date emplDob) {
        this.emplDob = emplDob;
    }

    public BigDecimal getEmplSalary() {
        return emplSalary;
    }

    public void setEmplSalary(BigDecimal emplSalary) {
        this.emplSalary = emplSalary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", emplName='" + emplName + '\'' +
                ", emplDob=" + emplDob +
                ", emplSalary=" + emplSalary +
                '}';
    }
}

