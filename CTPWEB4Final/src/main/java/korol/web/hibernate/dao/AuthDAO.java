package korol.web.hibernate.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import korol.web.hibernate.model.AuthUser;
import lombok.RequiredArgsConstructor;

@Component
@Transactional (readOnly = true)
@RequiredArgsConstructor
public class AuthDAO {
	@PersistenceContext
	private final EntityManager session;
	
	public AuthUser findByUsername(String username)
	{
		return session.createQuery("FROM AuthUser WHERE login = :login", AuthUser.class).setParameter("login", username).getSingleResult();
	}
}
