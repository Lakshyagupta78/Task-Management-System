package com.spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.dto.EmployeeLoginRequestDTO;
import com.spring.demo.entity.Employee;
import com.spring.demo.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService us;
	
	@PostMapping("/signup")
	public String signup(@RequestBody Employee user) {
		return us.signup(user);
	}
	
	@PostMapping("/login")
    public String login(@RequestBody EmployeeLoginRequestDTO loginRequestDTO) {
        return us.login(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
    }
}
