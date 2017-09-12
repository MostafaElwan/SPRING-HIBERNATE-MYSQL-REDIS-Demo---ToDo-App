package com.elwan.todo.dao.hibernate;

import com.elwan.todo.common.ConfigManager;
import com.elwan.todo.dao.DAOFactory;
import com.elwan.todo.dao.TodoDAO;
import com.elwan.todo.dao.UserDAO;

public class HibernateDAOFactory extends DAOFactory {
	
	public UserDAO getUserDAO() {
		return ConfigManager.getInstance().getBean(HibernateUserDAOImpl.class);
	}
	
	public TodoDAO getTodoDAO() {
		return ConfigManager.getInstance().getBean(HibernateTodoDAOImpl.class);
	}
	
}
