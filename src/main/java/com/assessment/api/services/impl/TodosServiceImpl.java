package com.assessment.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.api.dto.TodosDTO;
import com.assessment.api.entity.Todos;
import com.assessment.api.entity.UserDetails;
import com.assessment.api.mapper.TodosMapper;
import com.assessment.api.repository.TodosRepository;
import com.assessment.api.services.TodosService;
import com.assessment.api.services.UserDetailService;

@Service
public class TodosServiceImpl implements TodosService {

	@Autowired
	private TodosRepository todosRepository;

	@Autowired
	private TodosMapper todosMapper;

	@Autowired
	private UserDetailService userDetailService;

	@Override
	public List<TodosDTO> getAllTodos() {
		List<Todos> todos = todosRepository.findAll();
		if (!todos.isEmpty()) {
			return mapEntityListToDtoList(todos);
		}
		return null;
	}

	private List<TodosDTO> mapEntityListToDtoList(List<Todos> todos) {
		List<TodosDTO> todosDTO = new ArrayList<TodosDTO>();
		todos.stream().map(todo -> todosMapper.toDto(todo)).forEach(todo -> todosDTO.add(todo));
		return todosDTO;
	}

	@Override
	public TodosDTO getTodoById(Integer id) {
		if (null == id) {
			return null;
		}
		Optional<Todos> todos = todosRepository.findById(id);
		if (todos.isPresent()) {
			return todosMapper.toDto(todos.get());
		}
		return null;
	}

	@Override
	public List<TodosDTO> getTodosByUserId(Integer userId) {
		if (null == userId) {
			return null;
		}
		UserDetails userDetails = userDetailService.getUserDetailsByUserId(userId);
		Optional<List<Todos>> todos = todosRepository.findByUser(userDetails);
		if (todos.isPresent()) {
			return mapEntityListToDtoList(todos.get());
		}
		return null;
	}

	@Override
	public List<TodosDTO> getTodoByTitle(String title) {
		if (null == title) {
			return null;
		}
		Optional<List<Todos>> todos = todosRepository.findByTitle(title);
		if (todos.isPresent()) {
			return mapEntityListToDtoList(todos.get());
		}
		return null;
	}

	public List<TodosDTO> getTodoByStatus(Boolean completed) {
		if (null == completed) {
			return null;
		}
		Optional<List<Todos>> todos = todosRepository.findByCompleted(completed);
		if (todos.isPresent()) {
			return mapEntityListToDtoList(todos.get());
		}
		return null;
	}

	@Override
	public void addTodo(Integer userId, String title, Boolean completed) {
		Todos todos = new Todos();
		saveTodo(userId, title, completed, todos);
	}

	private void saveTodo(Integer userId, String title, Boolean completed, Todos todos) {
		todos.setTitle(title);
		UserDetails userDetails = userDetailService.getUserDetailsByUserId(userId);
		todos.setUser(userDetails);
		todos.setCompleted(completed);
		todosRepository.save(todos);
	}

	@Override
	public void updateTodo(Integer id, Integer userId, String title, Boolean completed) {
		if (null != id) {
			Optional<Todos> todos = todosRepository.findById(id);
			if (todos.isPresent()) {
				saveTodo(userId, title, completed, todos.get());
			}
		}
	}

	@Override
	public void deleteTodo(Integer id) {
		if (null != id) {
			Optional<Todos> todos = todosRepository.findById(id);
			if (todos.isPresent()) {
				todosRepository.delete(todos.get());
			}
		}
	}

}
