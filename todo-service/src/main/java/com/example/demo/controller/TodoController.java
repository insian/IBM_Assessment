package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.TodoListNotFoundException;
import com.example.demo.model.Todolist;
import com.example.demo.service.TodoService;
import com.example.demo.ui.ErrorResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/todolist")
public class TodoController {
	private final TodoService todoService;
	
	@ExceptionHandler(TodoListNotFoundException.class)
	public ErrorResponse handleException(TodoListNotFoundException e) {
		ErrorResponse response = new ErrorResponse();
		System.out.println(e.getMessage());
		System.out.println("A");
		response.setMessage(e.getMessage());
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setToOfError(System.currentTimeMillis());
		return response;
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ErrorResponse handleException(HttpMessageNotReadableException e) {
		ErrorResponse response = new ErrorResponse();
		response.setMessage("Please give TodoName from the defined list[compile, test, code, deploy]");
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setToOfError(System.currentTimeMillis());
		return response;
	}
	
	@GetMapping
	public ResponseEntity<?> listSchools(){
		return ResponseEntity.status(HttpStatus.OK).body(todoService.getAllTodoList());
	}
	
	@PostMapping
	public ResponseEntity<?> createSchool(@RequestBody Todolist todoList) throws HttpMessageNotReadableException{
		return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodolist(todoList));
	}
	
	@GetMapping("/{todoId}")
	public ResponseEntity<?> findSchoolById(@PathVariable int todoId) throws TodoListNotFoundException{
		return ResponseEntity.status(HttpStatus.OK).body(todoService.findByTodoId(todoId));
	}
	
	@PutMapping("/{todoId}")
	public void updateSchoolbyId(@PathVariable int todoId, @RequestBody Todolist todoList) throws TodoListNotFoundException, HttpMessageNotReadableException{
		todoService.updateTodolist(todoId, todoList);
	}
	
	@DeleteMapping("/{todoId}")
	public void delete(@PathVariable int todoId) throws TodoListNotFoundException{
		todoService.deleteByTodoId(todoId);
	}
}
