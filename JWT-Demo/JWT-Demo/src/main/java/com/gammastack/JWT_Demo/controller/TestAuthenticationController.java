package com.gammastack.JWT_Demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/test")
public class TestAuthenticationController {
	
	@GetMapping("/hello")
	public String helloEndPoint() {
		return "Hello Guys... EVERY USER is allowed to access this Api";
	}
	
	@GetMapping("/users")
	@PreAuthorize("hasRole('USER')")
	public String userEndPoint() {
		return "Hello Guys... only USER is allowed to access this Api";
	}
	
	@GetMapping("/admins")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminEndPoint() {
		return "Hello Guys... only ADMIN is allowed to access this Api";
	}

}
