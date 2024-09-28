package web.laba3.domparser.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors (chain = true)
public class Employee {
	 private int id;
	 private String name;
	 private int age;
	 private String gender;
	 private String role;
}
