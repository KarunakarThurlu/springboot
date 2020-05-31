package com.app;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.model.Role;
import com.app.model.User;
import com.app.service.IUserService;

@SpringBootApplication
public class SpringbootApplication {

	/*@Autowired
	private IUserService service;

	@PostConstruct
	public void initUsers() {
		User u=new User();
		Set<Role> r=new HashSet<Role>();
		Role rr=new Role();
		rr.setRoleName("USER");
		r.add(rr);
		u.setRoles(r);
		u.setUserEmail("karun@gmail.com");
		u.setUserPwd("karun");
		u.setUserName("karun");
		service.saveUser(u);
	}*/

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}
