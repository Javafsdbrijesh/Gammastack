package com.gammastack.JWT_Demo.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gammastack.JWT_Demo.dto.JwtAuthenticationResponse;
import com.gammastack.JWT_Demo.dto.SignInRequest;
import com.gammastack.JWT_Demo.dto.SignUpRequest;
import com.gammastack.JWT_Demo.model.Role;
import com.gammastack.JWT_Demo.model.Users;
import com.gammastack.JWT_Demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
	
	private final UserRepository userRepository;
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	//Singup Method	
	public JwtAuthenticationResponse signup(SignUpRequest request) {
		
		var user = Users
				.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.ROLE_USER)
				.build();
		user = userService.save(user);
		var jwt = jwtService.generateToken(user);
		
		log.debug("in signup - {}", jwt);
		return JwtAuthenticationResponse.builder().token(jwt).build();
	}
	
	//Singin method
	public JwtAuthenticationResponse signin(SignInRequest request) {
		
		authenticationManager.authenticate( 
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		
		var user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new IllegalArgumentException());
		
		var jwt  = jwtService.generateToken(user);
		
		log.debug("in signin - {}", jwt);
		
		return JwtAuthenticationResponse.builder().token(jwt).build();
	}

}
