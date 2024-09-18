package korol.web.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import korol.web.hibernate.model.Student;
import lombok.RequiredArgsConstructor;

@Service // возможно надо удалить
@Transactional (readOnly = true)
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
	@Autowired 
	private final EntityManagerFactory em;

	@Override
	public List <Student> fetchAll() {
		EntityManager session = em.createEntityManager();
		List<Student> students = session.createQuery("FROM Student", Student.class).getResultList();
		return students;
	}

	@Override
	public List<Student> fetchByNameLike(String name) {
		EntityManager session = em.createEntityManager();
		List<Student> students = session.createQuery("FROM Student WHERE fname || ' ' || lname LIKE :name", Student.class).setParameter("name", "%".concat(name).concat("%")).getResultList();
		return students;
	}

	@Override
	public Student fetchStudent(Integer id) throws NoResultException {
		EntityManager session = em.createEntityManager();
		Student student = session.createQuery("FROM Student WHERE id = :id", Student.class).setParameter("id", Integer.toString(id)).getSingleResult();
		return student;
	}

	@Override
	@Transactional
	public void addStudent(Student student) {
		EntityManager session = em.createEntityManager();
		session.persist(session);
	}
}
