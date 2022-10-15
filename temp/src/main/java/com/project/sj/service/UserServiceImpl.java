package com.project.sj.service;

import com.project.sj.domain.User;
import com.project.sj.repository.UserRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@Override
	public void join(User user) {
		userRepository.insert(user);
	}

	@Override
	public boolean login(String id, String pwd) {
		//입력된 id로 유저 객체를 받아옴
		User user = getUSer(id).orElseThrow();
		if(user==null||user.getId()==""||user.getId()==null){
			return false;
		}
		//id==pwd 일치
		if(pwd.equals(user.getPwd())){
			return true;
		}
		return false;
	}

	@Override
	public Optional<User> getUSer(String id) {
		return userRepository.getById(id);
	}
}
