package korol.web.hibernate.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import korol.web.hibernate.model.Person;
import lombok.RequiredArgsConstructor;

@Component
@Transactional (readOnly = true)
@RequiredArgsConstructor
public class PersonDAO {
	@PersistenceContext
	private final EntityManager session;

	@Transactional (readOnly = false)
	public void addPerson(Person person) {
		session.persist(person);
	}
	
	@Transactional (readOnly = false)
	public void updatePerson(Person person, Integer id) {
		Person oldPerson = session.find(Person.class, id);
		oldPerson.setAge(person.getAge()).
				setMail(person.getMail()).
				setSurname(person.getSurname()).
				setName(person.getName()).
				setPhone(person.getPhone());
	}
	
	@Transactional (readOnly = false)
	public void deletePerson(int id)
	{
		Person person = session.find(Person.class, id);
		session.remove(person);
	}
	
	public List <Person> showPeople() {
        List<Person> people = session.createQuery("FROM Person", Person.class).getResultList();
        return people;
    }

	public Person findPersonById(int id)
	{
		return session.find(Person.class, id);
	}

}
