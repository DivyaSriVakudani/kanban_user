package com.example.userservice.serv;

import java.util.List;

import com.example.userservice.model.User;

public interface UserService {

	public User saveUser (User newUser);
	
	public User loadUserByUsername(String username);
	
	public User loadUserById(Long id);
	
	public List<User> getAllUser();
	
	public User userLogin(String userName , String password);
	
	public List<User> getByPositon();
}
