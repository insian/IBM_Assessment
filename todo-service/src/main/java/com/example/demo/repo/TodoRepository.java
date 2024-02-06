package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Todolist;

public interface TodoRepository extends JpaRepository<Todolist, Integer>{
	
}
