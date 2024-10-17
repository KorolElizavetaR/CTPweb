package com.socket.webapp.repository;

import java.util.Optional;

import com.socket.webapp.model.User;

public interface UserRepository {
	Optional<User> findByUsername(String username);
}
