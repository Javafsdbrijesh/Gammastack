package com.gammastack.JWT_Demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gammastack.JWT_Demo.dto.JwtAuthenticationResponse;
import com.gammastack.JWT_Demo.dto.SignInRequest;
import com.gammastack.JWT_Demo.dto.SignUpRequest;
import com.gammastack.JWT_Demo.service.AuthenticationService;
import com.gammastack.JWT_Demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	private UserService userService;
	
	@PostMapping("/signup")
	public JwtAuthenticationResponse signup(@RequestBody SignUpRequest request) {
		
		return authenticationService.signup(request);
	}
	
	@PostMapping("/signin")
	public JwtAuthenticationResponse signin(@RequestBody SignInRequest request) {
		
		return authenticationService.signin(request);
		
	}
	
}
