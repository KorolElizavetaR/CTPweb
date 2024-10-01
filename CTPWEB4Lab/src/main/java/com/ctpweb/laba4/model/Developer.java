package com.ctpweb.laba4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Developer {
	private Integer id;
	private String firstName;
	private String lastName;
	private String specialty;
	private Integer experience;
}
