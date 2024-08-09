package com.training.user_service.view;

import com.training.user_service.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateView {
	private User user;
	private Department department;
}
