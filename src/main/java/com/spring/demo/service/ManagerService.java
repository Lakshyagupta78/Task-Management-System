package com.spring.demo.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.spring.demo.entity.Employee;
import com.spring.demo.entity.Manager;
import com.spring.demo.repository.ManagerRepo;

@Service
public class ManagerService {

	@Autowired
	private ManagerRepo mr; 
	
	public String signup(Manager user) {
        if (mr.findByUsername(user.getUsername()) != null) {
            return "Username already taken";
        }
        if (mr.findByEmail(user.getEmail()) != null) {
            return "Email already registered";
        }

        user.setPassword(encryptPassword(user.getPassword()));
        mr.save(user);
        return "Manager registered successfully";
    }
	
	
	public String login(String username, String password) {
        Manager user = mr.findByUsername(username);

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
	
	
	public Manager findById(Long id) {
        return mr.findById(id).orElse(null);
	}
}
