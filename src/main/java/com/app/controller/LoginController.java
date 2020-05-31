package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.jwtutil.JWTUtil;
import com.app.model.User;
import com.app.service.IUserService;

@RestController
public class LoginController {

	@Autowired
	private IUserService service;

	@Autowired
	private JWTUtil jwtUitl;

	@Autowired
	private AuthenticationManager authenticationManager;

	//@PreAuthorize("hasAnyRole('USER',ADMIN')")
	@RequestMapping("/login")
	public String getData(@RequestBody User user) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getUserPwd()));
		} catch (AuthenticationException e) {
			throw new Exception("username Invalid");
		}
		return jwtUitl.genarateToken(user.getUserName());
	}


	@RequestMapping("/admin/save")
	public String saveUser(@RequestBody User user) {
		service.saveUser(user);
		return "user saved successfully";
	}

	@RequestMapping("/user")
	public String getUserUrl() {
		return "user secured url";
	}
	
	@RequestMapping("/admin")
	public String getAdminUrl() {
		return "admin secured url";
	}
}
