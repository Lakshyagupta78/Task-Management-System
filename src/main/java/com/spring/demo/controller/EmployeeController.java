package com.spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.dto.EmployeeLoginRequestDTO;
import com.spring.demo.dto.TaskStatusUpdateDTO;
import com.spring.demo.entity.Employee;
import com.spring.demo.entity.Task;
import com.spring.demo.entity.TaskStatus;
import com.spring.demo.service.EmployeeService;
import com.spring.demo.service.TaskService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService us;
	
	@Autowired
	private TaskService taskService;
	
	
	@PostMapping("/signup")
	public String signup(@RequestBody Employee user) {
		return us.signup(user);
	}
	
	@PostMapping("/login")
    public String login(@RequestBody EmployeeLoginRequestDTO loginRequestDTO) {
        return us.login(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
    }
	
	 @PutMapping("/update-status")
	    public String updateTaskStatus(@RequestBody TaskStatusUpdateDTO taskStatusUpdateDTO) {
	        Long taskId = taskStatusUpdateDTO.getTaskId();
	        Long employeeId = taskStatusUpdateDTO.getEmployeeId();
	        TaskStatus status = taskStatusUpdateDTO.getStatus();

	        Task task = taskService.findById(taskId);

	        if (task == null) {
	            return "Task not found";
	        }

	        if (task.getEmployee().getId() != employeeId) {
	            return "You are not authorized to change the status of this task";
	        }

	        // Check if the status is valid
	        if (task.getStatus() == TaskStatus.PENDING && (status != TaskStatus.IN_PROGRESS && status != TaskStatus.COMPLETED)) {
	            return "Invalid status update. You can only change status to IN_PROGRESS or COMPLETED.";
	        }	

	        // Update the task status
	        task.setStatus(taskStatusUpdateDTO.getStatus());
	        taskService.save(task);

	        return "Task status updated successfully";
	    }
	}


