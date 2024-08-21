package com.spring.demo.dto;

import com.spring.demo.entity.TaskStatus;

public class TaskStatusUpdateDTO {
	private Long taskId;
    private Long employeeId;
    private TaskStatus status;
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public TaskStatus getStatus() {
		return status;
	}
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
    
    
}
