package com.example.userservice.serv;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.userservice.model.Project;

@FeignClient(url = "http://localhost:8082" , value = "PROJECT-SERVICE")
public interface ProjectService {
	
	@GetMapping("/api/project/all/{username}")
	List<Project> getAllProjects(@PathVariable String username);
	
}
