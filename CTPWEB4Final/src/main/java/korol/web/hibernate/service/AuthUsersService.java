package korol.web.hibernate.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import korol.web.hibernate.dao.AuthDAO;
import korol.web.hibernate.model.AuthUser;
import korol.web.hibernate.security.AuthUserDetails;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthUsersService implements UserDetailsService {
	@Autowired
	private final AuthDAO authDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional <AuthUser> user = Optional.of(authDAO.findByUsername(username));
		if (user.isEmpty())
			throw new UsernameNotFoundException("User " + username + " is not found");
		return new AuthUserDetails(user.get());
	}

}
