
package com.example.userservice.serv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userservice.exception.UsernameAlreadyExistsException;
import com.example.userservice.model.Project;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService{

	

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProjectService projectService;
    
    @Override
	public User userLogin(String userName, String password) {
    	System.out.println(userName);
		User user = userRepository.findByUsername(userName);
		System.out.println(user.getPassword());
		System.out.println(password);
		System.out.println(user.getPassword() == password);
		if(user != null && user.getPassword().equals(password)) {
			System.out.println("login");
			return user;
		}
		return null;
	}
 
	@Override
	public User saveUser (User newUser) {
		
		try {
			newUser.setPassword(newUser.getPassword());
//			Username has to be unique 
			newUser.setUsername(newUser.getUsername());
//			Don't persisting the password
			newUser.setConfirmPassword("");
			return userRepository.save(newUser);
		}
		catch(Exception error) {
			throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists!");
		}
	}
	
	@Override
	public User loadUserByUsername(String username){
		User user = userRepository.findByUsername(username);
		List<Project> projectList = projectService.getAllProjects(username);
		System.out.println(projectList);
		user.setProjects(projectList);
		System.out.println(user);
		return user;
	}
	
	
	@Override
	public User loadUserById(Long id) {
		User user = userRepository.getById(id);
		String username = user.getUsername();
		List<Project> projectList = projectService.getAllProjects(username);
		user.setProjects(projectList);
		return user;
	}

	@Override
	public List<User> getAllUser() {
		
		return (List<User>) userRepository.findAll();
	}

	@Override
	public List<User> getByPositon(){
		return userRepository.getByPosition("2");
	}

	
	
}

