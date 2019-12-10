package com.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repo.UserRepo;
import com.app.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Override
	public String saveUser(User user) {
		String pwd=bcrypt.encode(user.getUserPwd());
		user.setUserPwd(pwd);
		repo.save(user);
		return "saves";
	}

}
