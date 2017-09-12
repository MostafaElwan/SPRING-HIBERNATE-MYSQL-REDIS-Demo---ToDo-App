package com.elwan.todo.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.elwan.todo.common.AppLogger;
import com.elwan.todo.common.AppUtil;
import com.elwan.todo.exception.APIException;
import com.elwan.todo.model.Todo;
import com.elwan.todo.model.User;
import com.elwan.todo.service.TodoService;
import com.elwan.todo.service.UserService;

@Component
public class TodoDelegate {
	
	public static final AppLogger logger = new AppLogger(TodoDelegate.class);
	
	@Autowired
	private TodoService todoService;
	
	@Autowired	
	private UserService userService;

	public void createTodo(User user, Todo todo) throws APIException {
		user = userService.login(user.getUsername(), user.getPassword());
		AppUtil.throwExceptionIfNull(user, "Invalid username or password !");
		todo.setUser(user);
		todoService.upsert(todo);
		logger.info("A new todo {} has been created successfully.", todo);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=APIException.class)	
	public void createUser(User u) throws APIException {
		userService.register(u);
		createTodo(u, Todo.WELCOME);
		logger.info("A new user {} has been created with a welcome todo {}", u);
	}
	
	public List<User> getAllUsers() throws APIException {
		return userService.getAll();
	}

	public List<Todo> getTodoList(User u) throws APIException {
		List<Todo> todoList = todoService.all(u);
		AppUtil.throwExceptionIfNullOrEmpty(todoList, "No todo items have been found !");
		logger.info("{} todo items have been found for user {}", todoList.size(), u);
		return todoList;
	}
	
}
