package com.example.userservice.controller;

import java.util.List;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.model.User;
import com.example.userservice.serv.UserService;

@RequestMapping("/api/users")
@RestController
@CrossOrigin
public class UserController {
	
		
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/login/{username}/{password}")
	public User userLogin(@PathVariable String username, @PathVariable String password) {
	    return userService.userLogin(username, password);
	}
	
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user){					
		return userService.saveUser(user);		
//		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);		
	}
	
	@GetMapping("/username/{username}")
	public User loadByUserName(@PathVariable String username) {
		return userService.loadUserByUsername(username);
	}
	
	@GetMapping("/id/{id}")
	public User loadById(@PathVariable Long id) {
		return userService.loadUserById(id);
	}
	
	@GetMapping("/alluser")
	public ResponseEntity<List<User>> getAllUsers() {
	    try {
	        List<User> users = userService.getAllUser();
	        return ResponseEntity.ok(users);
	    } catch (Exception e) {
	        // Handle the exception and return an appropriate error response
	        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
	    }
	}

	@GetMapping("/getbyposition")
	public List<User> getByPositon(){
		return userService.getByPositon();
	}

}
