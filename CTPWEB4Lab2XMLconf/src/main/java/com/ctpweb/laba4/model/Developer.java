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
@NoArgsConstructor
public class Developer {
	private int id;
	private String firstName;
	private String lastName;
	private String specialty;
	private int experience;
	
	public Developer(String firstName, String lastName, String specialty, int experience) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.specialty = specialty;
		this.experience = experience;
	}
	
}
