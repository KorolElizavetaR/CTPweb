package web.parser.gson.parser;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.parser.gson.exclude.CustomExclusionStrategy;

public class Serialize {
	public static void ParseToJson(Object o, String filepath) throws IOException
	{
		Gson gson = new GsonBuilder().setExclusionStrategies(new CustomExclusionStrategy(List.class)).create();
		FileWriter writer = new FileWriter(filepath);
		gson.toJson(o, writer);
		System.out.println();
		writer.close();
	}
}
