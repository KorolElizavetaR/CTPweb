package web.parser.gson.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor (access = AccessLevel.PRIVATE)
@AllArgsConstructor (access = AccessLevel.PRIVATE)
public class Test {
	Integer varl;
	String val2;
	
	public static Test createData()
	{
		return new Test(1, "testme");
	}
}
