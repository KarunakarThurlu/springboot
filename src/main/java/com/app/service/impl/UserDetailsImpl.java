package com.app.service.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.model.User;

public class UserDetailsImpl implements UserDetails{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	
	
	 public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserDetailsImpl() {
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	return	user.getRoles().stream().map(role-> new SimpleGrantedAuthority("ROLE_"+role)).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getUserPwd();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
