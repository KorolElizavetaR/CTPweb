package web.parser.gson.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import com.google.gson.Gson;

import web.parser.gson.model.Staff;

public class Deserealize <T> {
	public static Object fromJsonToObject(String filepath) throws FileNotFoundException
	{
		 Gson gson = new Gson();
		 Reader reader = new FileReader(filepath);
		 return gson.fromJson(reader, Object.class);
	}
}
