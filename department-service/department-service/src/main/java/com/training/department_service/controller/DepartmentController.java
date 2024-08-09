package com.training.department_service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.department_service.entity.Department;
import com.training.department_service.service.DepartmentService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/department")
@Slf4j
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

	@PostMapping("/")
	public Department saveDepartment(@Valid @RequestBody Department department) {
		// Department department = departmentService.saveDepartment(department);
		LOGGER.info("Inside saveDepartment : " + department);
		return departmentService.saveDepartment(department);
	}

	@GetMapping("/{id}")
	public Department findDepartmentById(@PathVariable("id") Long departmentId) {
		LOGGER.info("Inside findDepartmentById get by ID: " + departmentId);
		return departmentService.findDepartmentById(departmentId);
	}

	@GetMapping("/")
	public List<Department> getAllDepartments() {
		LOGGER.info("Inside getAllDepartmens get by ID: ");
		List<Department> departmentList = departmentService.findAll();
		return departmentList;
	}

	@DeleteMapping("/{id}")
	public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
		LOGGER.info("Inside deleteDepartmentById delete by ID: " + departmentId);
		departmentService.deleteDepartmentById(departmentId);

		return "Deleted Successfully";
	}

	@PutMapping("/{id}")
	public Department updateDepartment(@PathVariable("id") Long departmentId, 
			@RequestBody Department department) {
		LOGGER.info("Inside updateDepartment  by ID: " + departmentId);
		Department departmentResponse = departmentService.updateDepartment(departmentId, department);
		return departmentResponse;

	}

}
