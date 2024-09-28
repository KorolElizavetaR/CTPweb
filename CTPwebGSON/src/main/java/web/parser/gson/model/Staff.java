package web.parser.gson.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import web.parser.gson.annotation.ExcludeField;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
	private String name;
	private int age;
	@ExcludeField
	private String[] position;
	@ExcludeField
	private List<String> skills;
	@ExcludeField
	private Map<String, BigDecimal> salary;
	
	public static Staff createStaff()
	{
		Staff staff = new Staff();
	      staff.setName("oleg");
	      staff.setAge(35);
	      staff.setPosition(new String[]{"Founder", "SEO", "coder"});
	      Map<String, BigDecimal> salary = new HashMap<String, BigDecimal>() {{
	         put("2010", new BigDecimal(10000));
	         put("2012", new BigDecimal(12000));
	         put("2018", new BigDecimal(14000));
	      }};
	      staff.setSalary(salary);
	      staff.setSkills(Arrays.asList("java", "python", "node", "kotlin"));
	      return staff;
	}
}
