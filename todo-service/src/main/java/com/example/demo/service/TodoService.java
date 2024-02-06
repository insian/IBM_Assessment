package com.example.demo.service;

import java.util.List;

import org.springframework.http.converter.HttpMessageNotReadableException;

import com.example.demo.exception.TodoListNotFoundException;
import com.example.demo.model.Todolist;

public interface TodoService {
	List<Todolist> getAllTodoList();
	Todolist createTodolist(Todolist todoList) throws HttpMessageNotReadableException;
	Todolist findByTodoId(int todoId) throws TodoListNotFoundException;
	void updateTodolist(int todoId, Todolist todoList) throws TodoListNotFoundException,HttpMessageNotReadableException;
	void deleteByTodoId(int todoId) throws TodoListNotFoundException;
}
