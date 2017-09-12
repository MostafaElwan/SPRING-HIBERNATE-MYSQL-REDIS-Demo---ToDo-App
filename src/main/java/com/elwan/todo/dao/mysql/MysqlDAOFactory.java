package com.elwan.todo.dao.mysql;

import com.elwan.todo.common.ConfigManager;
import com.elwan.todo.dao.DAOFactory;
import com.elwan.todo.dao.TodoDAO;
import com.elwan.todo.dao.UserDAO;

public class MysqlDAOFactory extends DAOFactory {
	
	public UserDAO getUserDAO() {
		return ConfigManager.getInstance().getBean(MysqlUserDAOImpl.class);
	}
	
	public TodoDAO getTodoDAO() {
		return ConfigManager.getInstance().getBean(MysqlTodoDAOImpl.class);
	}
	
}
