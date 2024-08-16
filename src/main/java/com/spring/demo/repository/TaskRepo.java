package com.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.demo.entity.Task;

public interface TaskRepo extends JpaRepository<Task, Long>{

}
