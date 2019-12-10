package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.User;
public interface UserRepo extends JpaRepository<User, Integer>{

	User findByUserName(String username);

}
