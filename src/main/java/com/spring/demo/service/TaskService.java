package com.spring.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.demo.dto.TaskAssignmentDTO;
import com.spring.demo.entity.Employee;
import com.spring.demo.entity.Manager;
import com.spring.demo.entity.Task;
import com.spring.demo.entity.TaskStatus;
import com.spring.demo.repository.EmployeeRepo;
import com.spring.demo.repository.ManagerRepo;
import com.spring.demo.repository.TaskRepo;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepo tr;
	
	@Autowired
	private ManagerRepo mr;
	
	@Autowired
	private EmployeeRepo er;
	
	public String createTask(TaskAssignmentDTO taskDTO) {
        // Check if employee and manager exist
        Optional<Employee> employeeOpt = er.findById(taskDTO.getEmployeeId());
        Optional<Manager> managerOpt = mr.findById(taskDTO.getManagerId());

        if (employeeOpt.isEmpty() || managerOpt.isEmpty()) {
            return "Employee or Manager not found";
        }

        Employee employee = employeeOpt.get();
        Manager manager = managerOpt.get();

        if (employee.getAdmin() == null) {
            return "Employee is not assigned to any Manager";
        }
        
        if (employee.getAdmin().getId() != manager.getId()) {
            return "Employee does not work under the specified Manager";
        }
        
        TaskStatus status;
        try {
            status = TaskStatus.valueOf(taskDTO.getStatus().toUpperCase());
        } catch (IllegalArgumentException e) {
            return "Error: Invalid task status";
        }

        
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setAssignedDate(taskDTO.getAssignedDate());
        task.setDueDate(taskDTO.getDueDate());
        task.setStatus(status);
        task.setEmployee(employee);
        task.setManager(manager);
        
        tr.save(task);

        return "Task created successfully";
    }
	
	 public List<Task> getAllTasks() {
	        return tr.findAll();
	    }
}
