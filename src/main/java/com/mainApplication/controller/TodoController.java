package com.mainApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mainApplication.model.Todo;
import com.mainApplication.model.TodoStatus;
import com.mainApplication.service.TodoService;

@Controller
@RequestMapping("/todos")
public class TodoController {

	@Autowired
	private TodoService todoService;

	
	 @GetMapping
	 @ResponseBody
	    public List<Todo> getAllTodos() {
	        return todoService.getAllTodos();
	    }

	    @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public Todo createTodo(@RequestBody Todo todo) {
	        return todoService.createTodo(todo);
	    }

	    @PatchMapping("/{id}/complete")
	    public Todo completeTodo(@PathVariable Long id) {
	        return todoService.updateTodoStatus(id, TodoStatus.COMPLETED);
	    }
}

@Controller
class IndexController{
	
	@GetMapping("/")
	public  String  index() {
		return "index";
	}
}





