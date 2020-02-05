package com.assessment.api.services;

import java.util.List;

import com.assessment.api.dto.TodosDTO;

public interface TodosService {

	public List<TodosDTO> getAllTodos();

	public TodosDTO getTodoById(Integer id);

	public List<TodosDTO> getTodosByUserId(Integer userId);

	public List<TodosDTO> getTodoByTitle(String title);
	
	public List<TodosDTO> getTodoByStatus(Boolean completed);

	public void addTodo(Integer userId, String title, Boolean completed);

	public void updateTodo(Integer id, Integer userId, String title, Boolean completed);

	public void deleteTodo(Integer id);

}
