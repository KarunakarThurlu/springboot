package com.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repo.UserRepo;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepo.findByUserName(username);
		UserDetailsImpl userDetails=null;
		if(user!=null) {
			userDetails=new UserDetailsImpl();
			userDetails.setUser(user);
		}
		else {
			throw new UsernameNotFoundException("user "+username+" dos't exist");
		}
		return userDetails;
	}

}
