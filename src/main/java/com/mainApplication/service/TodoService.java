package com.mainApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mainApplication.model.Todo;
import com.mainApplication.model.TodoStatus;
import com.mainApplication.repository.TodoRepository;

@Service
public class TodoService {

	@Autowired
	 public TodoRepository todoRepository;

    public Todo createTodo(Todo todo) {
        todo.setStatus(TodoStatus.PENDING);
        return todoRepository.save(todo);
    }

	    public List<Todo> getAllTodos() {
	        return todoRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
	    }

	    public Todo updateTodoStatus(Long id, TodoStatus status) {
	        Todo todo = todoRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Todo not found"));
	        todo.setStatus(status);
	        return todoRepository.save(todo);
	    }
}
