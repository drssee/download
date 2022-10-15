package com.project.sj.repository;

import com.project.sj.domain.User;

import java.util.Optional;

public interface UserRepository {
	void insert(User user);
	Optional<User> getById(String id);
}
