package korol.web.hibernate.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import korol.web.hibernate.model.Student;

@Service
public interface StudentService {
	List <Student> fetchAll();
	
	List <Student> fetchByNameLike(String name);
	
	Student fetchStudent(Integer id);
	
	void addStudent(Student student);
}
