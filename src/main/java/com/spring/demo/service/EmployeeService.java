package com.spring.demo.service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.demo.entity.Employee;
import com.spring.demo.repository.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo ur;
	
	public String signup(Employee user) {
        if (ur.findByUsername(user.getUsername()) != null) {
            return "Username already taken";
        }
        if (ur.findByEmail(user.getEmail()) != null) {
            return "Email already registered";
        }

        user.setPassword(encryptPassword(user.getPassword()));
        ur.save(user);
        return "User registered successfully";
    }
	
	
	public String login(String username, String password) {
        Employee user = ur.findByUsername(username);

        if (user == null) {
            return "User not found";
        }

        // Verify the password (consider stronger password handling in real applications)
        String encryptedPassword = encryptPassword(password);
        if (!user.getPassword().equals(encryptedPassword)) {
            return "Invalid password";
        }

        return "Login successful";
    }
	
	private String encryptPassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
	
	
	public Employee findById(Long id) {
        return ur.findById(id).orElse(null);
    }

    public Employee save(Employee employee) {
        return ur.save(employee);
    }
	
}
