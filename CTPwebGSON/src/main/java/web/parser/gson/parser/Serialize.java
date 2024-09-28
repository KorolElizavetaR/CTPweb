package web.parser.gson.parser;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.parser.gson.model.Staff;

public class Serialize {
	public static void ParseToJson(Object o, String filepath) throws IOException
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter writer = new FileWriter(filepath);
		gson.toJson(o, writer);
		System.out.println();
		writer.close();
	}
}
