package com.project.sj.service;

import com.project.sj.domain.User;

import java.util.Optional;

public interface UserService {
	public void join(User user);
	public boolean login(String id,String pwd);
	public Optional<User> getUSer(String id);
}
