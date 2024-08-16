package com.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

//import com.spring.demo.entity.Employee;
import com.spring.demo.entity.Manager;

public interface ManagerRepo extends JpaRepository<Manager,Long> {
	
	Manager findByUsername(String username);
	
	Manager findByEmail(String email);
}
