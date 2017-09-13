package com.elwan.todo.dao;

import java.util.List;

import com.elwan.todo.exception.APIException;
import com.elwan.todo.model.Todo;
import com.elwan.todo.model.User;

public interface TodoDAO extends DAO<Todo> {
	
	List<Todo> all(User user) throws APIException;

}
