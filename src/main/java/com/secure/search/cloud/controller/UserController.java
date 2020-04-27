package com.secure.search.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.secure.search.cloud.model.User;
import com.secure.search.cloud.repository.UserRepository;
import com.secure.search.cloud.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository repo;
	
	
	@PostMapping("/save")
	public String saveUser(@RequestBody List<User> user){
		repo.save(user);
		return "No of User is save as :"+user.size();
	}
	
	
	@GetMapping("/searchMultiField/{firstname}/{age}")
	public List<User> searchMultiField(@PathVariable String firstname,@PathVariable int age){
		return service.searchMultiField(firstname, age);
	}
	
	@GetMapping("/searchPartialQuery/{desc}")
	public List<User> searchPartialQuery(@PathVariable String desc){
		return service.searchPartialQuery(desc);
	}
	
	@GetMapping("/multiMatchQuery/{input}")
	public List<User> multiMatchQuery(@PathVariable String input){
		return service.multiMatchQuery(input);
	}
	
	
	

}



