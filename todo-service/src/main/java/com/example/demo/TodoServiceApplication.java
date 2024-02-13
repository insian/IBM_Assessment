package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.TodoName;
import com.example.demo.model.Todolist;
import com.example.demo.repo.TodoRepository;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class TodoServiceApplication {
	private final TodoRepository todoRepository;
	public static void main(String[] args) {
		SpringApplication.run(TodoServiceApplication.class, args);
	}
	
	@PostConstruct
	void init() {
		List<Todolist> list = Stream.of(
				new Todolist(TodoName.compile,true,"25-01-2024"),
				new Todolist(TodoName.code,true,"31-01-2024"),
				new Todolist(TodoName.test,false,"04-02-2024"),
				new Todolist(TodoName.deploy,false,"04-02-2024")).collect(Collectors.toList());
		for(Todolist t: list) {
			todoRepository.save(t);
		}
	}

}
