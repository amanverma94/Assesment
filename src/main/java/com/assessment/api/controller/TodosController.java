package com.assessment.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.api.dto.TodosDTO;
import com.assessment.api.services.TodosService;

@RestController
@RequestMapping(value = "/api/Todos/")
public class TodosController {

	@Autowired
	private TodosService todosService;

	@GetMapping
	public List<TodosDTO> getAllTodos() {
		return todosService.getAllTodos();
	}

	@GetMapping("{id}")
	public TodosDTO getTodoById(@PathVariable Integer id) {
		return todosService.getTodoById(id);
	}

	@GetMapping("user")
	public List<TodosDTO> getTodosByUserId(@RequestParam Integer userId) {
		return todosService.getTodosByUserId(userId);
	}

	@GetMapping("title")
	public List<TodosDTO> getTodoByTitle(@RequestParam String title) {
		return todosService.getTodoByTitle(title);
	}

	@GetMapping("status")
	public List<TodosDTO> getTodoByStatus(@RequestParam Boolean completed) {
		return todosService.getTodoByStatus(completed);
	}

	@PostMapping("add")
	public void addTodo(@RequestParam Integer userId, @RequestParam String title, @RequestParam Boolean completed) {
		if (!StringUtils.isEmpty(userId)) {
			todosService.addTodo(userId, title, completed);
		}
	}

	@PutMapping
	public void updateTodo(@RequestParam Integer id, @RequestParam Integer userId, @RequestParam String title,
			@RequestParam Boolean completed) {
		todosService.updateTodo(id, userId, title, completed);
	}

	@DeleteMapping
	public void deleteTodo(@RequestParam Integer id) {
		todosService.deleteTodo(id);
	}

}
