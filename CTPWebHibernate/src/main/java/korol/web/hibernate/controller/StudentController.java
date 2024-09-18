package korol.web.hibernate.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.NoResultException;
import korol.web.hibernate.model.Student;
import korol.web.hibernate.service.StudentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping ("students")
public class StudentController {
	@Autowired
	private final StudentService studentService;
	
	@GetMapping("all")
	public List<Student> getStudents(@RequestParam (value = "name", required = false) String name)
	{
		if (name != null && !(name.isBlank()))
		{
			return studentService.fetchByNameLike(name);
		}
		return studentService.fetchAll();
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable ("id") Integer id)
	{
		try
		{
			return ResponseEntity.ok(studentService.fetchStudent(id));
		}
		catch(NoResultException ex)
		{
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping ("/add")
	public ResponseEntity<Student> addStudent(Student student)
	{
		try
		{
			studentService.addStudent(student);
			return ResponseEntity.ok(studentService.fetchStudent((int) student.getId()));
		}
		catch(Exception ex)
		{
			return ResponseEntity.unprocessableEntity().build();
		}
	}
}
