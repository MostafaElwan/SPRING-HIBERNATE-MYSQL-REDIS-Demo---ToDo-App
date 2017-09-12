package com.elwan.todo.service;

import java.util.List;

import com.elwan.todo.exception.APIException;
import com.elwan.todo.model.Todo;
import com.elwan.todo.model.User;

public interface TodoService {
	
	List<Todo> all() throws APIException;
	
	List<Todo> all(User user) throws APIException;
	
	Todo get(long id) throws APIException;
	
	void upsert(Todo todo) throws APIException;
	
	void delete(long id) throws APIException;
	
}
