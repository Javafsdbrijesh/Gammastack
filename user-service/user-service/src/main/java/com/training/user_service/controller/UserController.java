package com.training.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.user_service.entity.User;
import com.training.user_service.service.UserService;
import com.training.user_service.view.ResponseTemplateView;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	@CrossOrigin
	public User saveUer(@Valid @RequestBody User request) {
		User response = userService.saveUser(request);
		return response;
		
	}
	
	@GetMapping("/{id}")
	public User findUserById(@PathVariable("id") Long userId) {
		User response = userService.findUserById(userId);
		return response;
		
	}
	
	@GetMapping("/user/{id}")
	public  ResponseTemplateView  getUserByDepartmentId(@PathVariable("id") Long id) {
		
		ResponseTemplateView response = userService.getUserByDepartmentId(id);
		return response;
		
	}
	

}
