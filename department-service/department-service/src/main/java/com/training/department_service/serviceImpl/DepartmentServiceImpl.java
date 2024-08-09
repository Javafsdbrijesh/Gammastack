package com.training.department_service.serviceImpl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.department_service.entity.Department;
import com.training.department_service.exception.DepartmentNotFoundException;
import com.training.department_service.repository.DepartmentRepository;
import com.training.department_service.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	private final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);
	
	

	@Override
	public Department saveDepartment(Department department) {

		Department departmentResp = departmentRepository.save(department);
		
		return departmentResp;
	}

	@Override
	public Department findDepartmentById(Long departmentId) {
		Department department = departmentRepository.findByDepartmentId(departmentId);
		//throw new DepartmentNotFoundException("Department does not exist");
		if(department == null) {
			LOGGER.info("Department does not exist for depratment id : " + departmentId);
			throw new DepartmentNotFoundException("Department does not exist for depratment id : " + departmentId);
		}
		return department;
	}
	
	@Override
	public void deleteDepartmentById(Long departmentId) {
		LOGGER.info("in deleteDepartmentById: Find Department by Id " + departmentId);
		departmentRepository.deleteById(departmentId);
	}

	@Override
	public List<Department> findAll() {
		List<Department> departmentList = departmentRepository.findAll();
		LOGGER.info("in findAll : Find all Departments in database : " );
		return departmentList;
	}

	@Override
	public Department updateDepartment(Long departmentId, Department department) {
		Department departmentDB = departmentRepository.findById(departmentId).get(); 
		
		if(Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())) {
			departmentDB.setDepartmentName(department.getDepartmentName());
		}
		if(Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())) {
			departmentDB.setDepartmentAddress(department.getDepartmentAddress());
		}
		if(Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())) {
			departmentDB.setDepartmentCode(department.getDepartmentCode());
		}
		
		return departmentRepository.save(departmentDB);
	}
	
	
	

}
