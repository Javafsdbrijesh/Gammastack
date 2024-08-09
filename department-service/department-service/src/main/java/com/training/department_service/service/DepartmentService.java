package com.training.department_service.service;

import java.util.List;

import com.training.department_service.entity.Department;

public interface DepartmentService {

	Department saveDepartment(Department department);

	Department findDepartmentById(Long departmentId);

	void deleteDepartmentById(Long departmentId);

	List<Department> findAll();

	Department updateDepartment(Long departmentId, Department department);


}
