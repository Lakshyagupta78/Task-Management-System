package com.spring.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name = "assigned_date")
	private Date assignedDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "due_date")
	private Date dueDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private TaskStatus status;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Manager manager;

	public Task(Long id, String title, String description, Date assignedDate, Date dueDate, TaskStatus status,
			Employee employee, Manager manager) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.assignedDate = assignedDate;
		this.dueDate = dueDate;
		this.status = status;
		this.employee = employee;
		this.manager = manager;
	}

	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
}
