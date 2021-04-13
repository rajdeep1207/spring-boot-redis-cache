package com.rajdeep.rediscache.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajdeep.rediscache.ResourceNotFoundException;
import com.rajdeep.rediscache.entity.Employee;
import com.rajdeep.rediscache.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	public Employee getEmployee(int id) {
		return employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee not found" + id));
	}

	public Employee updateEmployee(int id, Employee employeedetails) {
		employeedetails.setId(id);
		return employeeRepository.save(employeedetails);
		
	}

	public void deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);
		
	}
}
