package web.jdbc.laba2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import web.jdbc.laba2.action.StudentAction;
import web.jdbc.laba2.config.ConfigClass;
import web.jdbc.laba2.pojo.Student;

public class StudentTest {
	static AnnotationConfigApplicationContext context;
	static StudentAction st;
	
	@BeforeAll
	static void initst()
	{
		context = new AnnotationConfigApplicationContext(ConfigClass.class);
		st = context.getBean("studentAction", StudentAction.class);
	}
	
	@AfterAll
	static void closeContext()
	{
		context.close();
	}
	
	@Test
	void fetchAllTest() {
		assertDoesNotThrow(() -> st.fetchAll());
	}
	
	static Stream <Student> students() {
	    return Stream.of(new Student("Maxim", "Arbuz", "+375(29)126-11-11", "awdsad@gmail.com", "Minsk", Date.valueOf("2003-12-17")),
				new Student("Katya", "Saburova", "+375(44)000-11-11", "katya@gmail.com", "Minsk", Date.valueOf("2001-07-01")));
	}
	
	@ParameterizedTest
	@MethodSource ("students")
	void insertTest(Student student) {
		assertDoesNotThrow(() -> st.insert(student));
	}
	
	@Test
	void updateTest() {
		assertDoesNotThrow(() -> st.update(st.fetchByIdObj((long)1).setAddress("Kanava").setMobileNo("+375(44)666-66-11")));
	}
}
