package com.spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.dto.TaskAssignmentDTO;
import com.spring.demo.entity.Employee;
import com.spring.demo.entity.Task;
import com.spring.demo.service.TaskService;
import com.spring.demo.dto.TaskResponseDTO;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskService ts;
	
	@PostMapping("/create")
    public String createTask(@RequestBody TaskAssignmentDTO taskDTO) {
        return ts.createTask(taskDTO);
    }
	
	@GetMapping("/all")
    public List<TaskResponseDTO> getAllTasks() {
        List<Task> tasks = ts.getAllTasks();
        return tasks.stream().map(task -> {
            TaskResponseDTO taskDTO = new TaskResponseDTO();
            taskDTO.setTaskId(task.getId());
            taskDTO.setTitle(task.getTitle());
            taskDTO.setDescription(task.getDescription());
            taskDTO.setAssignedDate(task.getAssignedDate());
            taskDTO.setDueDate(task.getDueDate());
            taskDTO.setStatus(task.getStatus().toString());
            taskDTO.setEmployeeName(task.getEmployee().getUsername());
            taskDTO.setManagerName(task.getManager().getUsername());
            return taskDTO;
        }).collect(Collectors.toList());
    }
}
