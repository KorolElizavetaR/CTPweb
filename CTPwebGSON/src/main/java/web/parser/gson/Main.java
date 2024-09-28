package web.parser.gson;

import java.io.IOException;

import web.parser.gson.model.Staff;
import web.parser.gson.model.Test;
import web.parser.gson.parser.Deserealize;
import web.parser.gson.parser.Serialize;

public class Main {
	private static final String STAFF_PATH = "staff.json";
	private static final String TEST_PATH = "src/main/resources/test.json";
	
	public static void main(String[] args) {
		try {
			Serialize.ParseToJson(Staff.createStaff(), STAFF_PATH);
			System.out.println(Deserealize.fromJsonToObject(STAFF_PATH));

			Serialize.ParseToJson(Test.createData() ,TEST_PATH);
			System.out.println(Deserealize.fromJsonToObject(TEST_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
