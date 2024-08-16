package com.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.demo.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	
	Employee findByUsername(String username);
	
	Employee findByEmail(String email);
}
