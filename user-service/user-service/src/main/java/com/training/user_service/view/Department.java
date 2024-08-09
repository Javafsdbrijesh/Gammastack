package com.training.user_service.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
	private Long departmentId;
	private String depatmentName;
	private String departmentAddress;
	private String departmentCode;

}
