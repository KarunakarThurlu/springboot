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
    
	UserDetailsImpl userDetails=null;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepo.findByUserName(username);
		userDetails=new UserDetailsImpl();
		userDetails.setUser(user);
		return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getUserPwd(),userDetails.getAuthorities());
	}

}
