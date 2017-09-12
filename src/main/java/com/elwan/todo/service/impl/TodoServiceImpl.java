package com.elwan.todo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.elwan.todo.common.AppConstant;
import com.elwan.todo.common.ConfigManager;
import com.elwan.todo.dao.DAOFactory;
import com.elwan.todo.dao.TodoDAO;
import com.elwan.todo.exception.APIException;
import com.elwan.todo.model.Todo;
import com.elwan.todo.model.User;
import com.elwan.todo.service.AbstractService;
import com.elwan.todo.service.TodoService;

public class TodoServiceImpl extends AbstractService implements TodoService {

	@Override
	public List<Todo> all() {
		logger.info("Returning all todo list in the system");
		return new ArrayList<>();
	}

	@Override
	public List<Todo> all(User user) throws APIException {
		DAOFactory factory = DAOFactory.getDAOFactory(ConfigManager.getInstance().getInt(AppConstant.Keys.DB_TYPE));
		TodoDAO dao = factory.getTodoDAO();
		List<Todo> todoList = dao.all(user);
		return todoList;
	}

	@Override
	public Todo get(long id) {
		logger.info("Get todo with id [{}]", id);
		return new Todo();
	}

	@Override
	public void upsert(Todo todo) throws APIException {
		DAOFactory factory = DAOFactory.getDAOFactory(ConfigManager.getInstance().getInt(AppConstant.Keys.DB_TYPE));
		TodoDAO dao = factory.getTodoDAO();
		if(todo.getId() == 0) {
			long id = dao.create(todo);
			todo.setId(id);
		} else {
			dao.update(todo);
		}
	}

	@Override
	public void delete(long id) {
		logger.info("Deleting todo with id [{}]", id);
	}

}
