package com.gammastack.JWT_Demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gammastack.JWT_Demo.model.Role;
import com.gammastack.JWT_Demo.model.Users;
import com.gammastack.JWT_Demo.repository.UserRepository;
import com.gammastack.JWT_Demo.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner{

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserService userService;
	
	@Override
	public void run(String... args) throws Exception {
		
		if(userRepository.count() == 0) {
			
			Users admin = Users
					.builder()
					.firstName("admin")
					.lastName("admin")
					.email("admin@admin.com")
					.password(passwordEncoder.encode("password"))
					.role(Role.ROLE_ADMIN)
					.build();
			
			userService.save(admin);
			log.debug("created ADMIN user - {} ", admin);
		}
		
	}

}
