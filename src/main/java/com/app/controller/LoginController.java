package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.User;
import com.app.service.IUserService;

@RestController
@RequestMapping("/app")
public class LoginController {
	
	@Autowired
	private IUserService service;
	
	@RequestMapping("/data")
	public String getData() {
		System.out.println("$$$$$$$$$$$$$$$Logincontroller$$$$$$$$$$$$$$$$");
		return "springBoot";
	}
	
	@RequestMapping("/save")
	public String saveUser(@RequestBody User user) {
		service.saveUser(user);
		return "user saved successfully";
	}

}
