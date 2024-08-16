package com.spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.spring.demo.dto.EmployeeLoginRequestDTO;
import com.spring.demo.dto.ManagerLoginRequest;
//import com.spring.demo.entity.Employee;
import com.spring.demo.entity.Manager;
import com.spring.demo.service.ManagerService;

@RestController
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	private ManagerService ms;
	
	@PostMapping("/signup")
	public String signup(@RequestBody Manager man) {
		return ms.signup(man);
	}
	
	@PostMapping("/login")
    public String login(@RequestBody ManagerLoginRequest loginRequestDTO) {
        return ms.login(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
    }
}
