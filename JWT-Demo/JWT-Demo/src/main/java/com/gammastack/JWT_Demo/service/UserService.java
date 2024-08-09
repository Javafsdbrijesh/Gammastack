package com.gammastack.JWT_Demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gammastack.JWT_Demo.model.Users;
import com.gammastack.JWT_Demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				// TODO Auto-generated method stub
				return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Does Not exist"));
			}
		};
	}
	
	//method: saves user to repository
	public Users save(Users newUser) {
		return userRepository.save(newUser);
	}
	
	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}


}
