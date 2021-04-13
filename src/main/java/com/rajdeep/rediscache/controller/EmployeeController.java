package com.rajdeep.rediscache.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajdeep.rediscache.ResourceNotFoundException;
import com.rajdeep.rediscache.entity.Employee;
import com.rajdeep.rediscache.model.EmployeeErrorResponse;
import com.rajdeep.rediscache.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/add")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}
	
	@GetMapping("/fetch")
	public List<Employee> getEmployees() {
		return employeeService.getEmployees();
	}
	
	@GetMapping("/{id}")
	@Cacheable(value = "employees",key = "#id")
	public Employee getEmployee(@PathVariable int id) {
		return employeeService.getEmployee(id);
	}
	
	@PutMapping("/{id}")
	@CachePut(value = "employees",key = "#id")
	public Employee updateEmployee(@PathVariable int id,@RequestBody Employee employeedetails) {
		getEmployee(id);
		return employeeService.updateEmployee(id,employeedetails);
	}
	
	@DeleteMapping("/{id}")
	@CacheEvict(value = "employees",key = "#id")
	public void deleteEmployee(@PathVariable int id) {
		Employee employee=getEmployee(id);
		employeeService.deleteEmployee(employee);
	}
	
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleException(ResourceNotFoundException ex) {
		return new ResponseEntity<>(
				new EmployeeErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), System.currentTimeMillis()),
				HttpStatus.NOT_FOUND);
	}

}
