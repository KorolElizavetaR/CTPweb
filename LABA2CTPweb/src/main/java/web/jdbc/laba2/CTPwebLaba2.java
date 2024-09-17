package web.jdbc.laba2;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import web.jdbc.laba2.DAO.ReadTables;
import web.jdbc.laba2.DAO.Tasks;


public class CTPwebLaba2 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigClass.class);
		ReadTables read = context.getBean("read", ReadTables.class);
		Tasks tasks = context.getBean("tasks", Tasks.class);
		try {
			tasks.task7();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		context.close();
	}

}