package com.userservice.service;

import java.util.List;

import com.userservice.model.User;

public interface UserService {
	

	 User saveUser(User user);
	 
	 List<User> getAllUsers();
	 
	 User getUser(String id);
	 
	public  void deleteUser(String id);
	 
}
