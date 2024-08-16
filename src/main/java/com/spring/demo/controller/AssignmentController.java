package com.spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.dto.AssignmentRequestDTO;
import com.spring.demo.entity.Employee;
import com.spring.demo.entity.Manager;
import com.spring.demo.service.EmployeeService;
import com.spring.demo.service.ManagerService;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {

	@Autowired
    private EmployeeService employeeService;

    @Autowired
    private ManagerService managerService;

    @PostMapping("/assign")
    public String assignEmployeeToManager(@RequestBody AssignmentRequestDTO assignmentRequest) {
    	Long employeeId = assignmentRequest.getEmployeeId();
        Long managerId = assignmentRequest.getManagerId();
        
        Employee employee = employeeService.findById(employeeId);
        Manager manager = managerService.findById(managerId);

        if (employee == null || manager == null) {
            return "Employee or Manager not found";
        }
        
        if (employee.getAdmin() != null) {
            return "Employee is already assigned to a Manager";
        }

        employee.setAdmin(manager);
        employeeService.save(employee);

        return "Employee assigned to Manager successfully";
    }
    
    @DeleteMapping("/deallocate")
    public String deallocateEmployeeFromManager(@RequestBody AssignmentRequestDTO assignmentRequest) {
        Long employeeId = assignmentRequest.getEmployeeId();
        Long managerId = assignmentRequest.getManagerId();
        
        Employee employee = employeeService.findById(employeeId);
        Manager manager = managerService.findById(managerId);

        if (employee == null || manager == null) {
            return "Employee or Manager not found";
        }

        if (employee.getAdmin().getId() != managerId) {
            return "Employee is not assigned to this Manager";
        }

        employee.setAdmin(null);
        employeeService.save(employee);

        return "Employee deallocated from Manager successfully";
    }
}
