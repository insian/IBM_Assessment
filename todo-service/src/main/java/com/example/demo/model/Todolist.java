package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "todolist")
public class Todolist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "todoId")
	private int todoId;
	
	@Column(name = "todoName")
	private TodoName todoName;
	
	@JsonProperty
	@Column(name = "isCompleted")
	private Boolean isCompleted;
	
	@Column(name = "completionDate")
	private String completionDate;

	public Todolist(TodoName todoName, Boolean isCompleted, String completionDate) {
		super();
		this.todoName = todoName;
		this.isCompleted = isCompleted;
		this.completionDate = completionDate;
	}
	
}
