package com.training.user_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.training.user_service.entity.User;
import com.training.user_service.repository.UserRepository;
import com.training.user_service.service.UserService;
import com.training.user_service.view.Department;
import com.training.user_service.view.ResponseTemplateView;

import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
	
	private static final String USERSERVICE = "userService";
	private static final String DEPARTMENT_SERVICE = "\"http://DEPARTMENT-SERVICE/department/\"";
	
	Long fallbackUserId;
	int attempt = 1;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public User saveUser(@Valid User request) {
		User response = userRepository.save(request);
		return response;
	}

	@Override
	public User findUserById(Long userId) {
		User response = userRepository.findById(userId).get();
		return response;
	}

	@Retry(name = USERSERVICE, fallbackMethod = "fallback_getUserByDepartmentId")
	public ResponseTemplateView getUserByDepartmentId(Long id) {
		ResponseTemplateView view = new ResponseTemplateView();
		log.info("Requesting attempt : " + attempt++);
		fallbackUserId = id;
		User user = userRepository.findById(id).get();
		log.info("connecting with department service");
		Department department = restTemplate.getForObject(DEPARTMENT_SERVICE + user.getDepartmentId(), Department.class);
		view.setUser(user);
		view.setDepartment(department);		
		log.info("Successfully connected: " + view);
		return view;
	}
	public ResponseTemplateView fallback_getUserByDepartmentId(Exception exception) {
		log.info("Fallback Method was called");
		ResponseTemplateView view = new ResponseTemplateView();
		User user = userRepository.findById(fallbackUserId).get();
		view.setUser(user);
		return view;
	}

}
