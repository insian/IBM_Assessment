package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import com.example.demo.exception.TodoListNotFoundException;
import com.example.demo.model.Todolist;
import com.example.demo.repo.TodoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
	private final TodoRepository todoRepository;
	@Override
	public List<Todolist> getAllTodoList() {
		// TODO Auto-generated method stub
		return todoRepository.findAll();
	}

	@Override
	public Todolist createTodolist(Todolist todoList) throws HttpMessageNotReadableException {
		// TODO Auto-generated method stub
		todoRepository.save(todoList);
		return todoList;
	}

	@Override
	public Todolist findByTodoId(int todoId) throws TodoListNotFoundException{
		// TODO Auto-generated method stub
		Optional<Todolist> result = todoRepository.findById(todoId);
		Todolist theTodolist = null;
		System.out.println(result.isPresent());
		if(result.isPresent()) {
			theTodolist = result.get();
		}else {
			System.out.println("ABC-123");
			throw new TodoListNotFoundException("todo list with id "+todoId+" not found");
		}
		return theTodolist;
	}


	@Override
	public void updateTodolist(int todoId, Todolist todoList) throws TodoListNotFoundException,HttpMessageNotReadableException {
		// TODO Auto-generated method stub
		Todolist theTodolist = findByTodoId(todoId);
		theTodolist.setTodoName(todoList.getTodoName());
		theTodolist.setIsCompleted(todoList.getIsCompleted());
		theTodolist.setCompletionDate(todoList.getCompletionDate());
		todoRepository.save(theTodolist);
	}
	
	@Override
	public void deleteByTodoId(int todoId) throws TodoListNotFoundException {
		// TODO Auto-generated method stub
		Todolist theTodolist = findByTodoId(todoId);
		todoRepository.delete(theTodolist);
	}

}
