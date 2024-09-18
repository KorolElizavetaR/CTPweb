package korol.web.hibernate.service;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import korol.web.hibernate.model.Student;
import lombok.RequiredArgsConstructor;

@Service // возможно надо удалить
@Transactional (readOnly = true)
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

	@PersistenceContext
	private EntityManager session;

	@Override
	public List <Student> fetchAll() {
		List<Student> students = session.createQuery("FROM Student", Student.class).getResultList();
		return students;
	}

	@Override
	public List<Student> fetchByNameLike(String name) {
		List<Student> students = session.createQuery("FROM Student WHERE fname || ' ' || lname LIKE :name", Student.class).setParameter("name", "%".concat(name).concat("%")).getResultList();
		return students;
	}

	@Override
	public Student fetchStudent(Integer id) throws NoResultException {
		Student student = session.createQuery("FROM Student WHERE id = :id", Student.class).setParameter("id", Integer.toString(id)).getSingleResult();
		return student;
	}

	@Override
	@Transactional (readOnly = false)
	public void addStudent(Student student) {
		session.persist(student);
	}

	@Override
	@Transactional (readOnly = false)
	public void editStudent(Student updateStudent, Integer id) throws IllegalArgumentException, IllegalAccessException {
		Field[] studentFields = student.getClass().getDeclaredFields();
		Student st = session.find(Student.class, id);
		for (Field field : studentFields)
		{
			field.setAccessible(true);
			if (field.get(updateStudent) != null)
			{
				sysout
			}
			field.setAccessible(false);
		}
	}
}
