package com.ctpweb.laba4.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "developer")
public class Developer {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column (nullable = false)
	private String firstName;
	
	@Column (nullable = false)
	private String lastName;
	
	@Column (nullable = false)
	private String specialty;
	
	@Column (nullable = false)
	private Integer experience;
	
	@Column
	private Integer salary;
	
	public Developer(String firstName, String lastName, String specialty, Integer experience, Integer salary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.specialty = specialty;
		this.experience = experience;
		this.salary = salary;
	}
	
}
