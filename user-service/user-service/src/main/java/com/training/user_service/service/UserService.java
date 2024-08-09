package com.training.user_service.service;

import com.training.user_service.entity.User;
import com.training.user_service.view.ResponseTemplateView;

import jakarta.validation.Valid;

public interface UserService {
	User saveUser(@Valid User request);

	User findUserById(Long userId);

	ResponseTemplateView getUserByDepartmentId(Long id);

}
