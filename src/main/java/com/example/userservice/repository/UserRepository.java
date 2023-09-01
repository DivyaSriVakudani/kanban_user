package com.example.userservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.userservice.model.User;



@Repository
public interface UserRepository extends CrudRepository<User, String>{

	User findByUsername(String username);
	User getById(Long id);
	List<User> findAll();
	List<User> getByPosition(String position);
}
