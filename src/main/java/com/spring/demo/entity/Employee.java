package com.spring.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
//	@Column(name = "role")
//	private String role;
	
	@Column(name = "email")
	private String email;
	
	@ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager admin;

	public Manager getAdmin() {
		return admin;
	}

	public void setAdmin(Manager admin) {
		this.admin = admin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee(long id, String username, String password, String email, Manager admin) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.admin = admin;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", admin=" + admin + "]";
	}

	

	

	
	
}
