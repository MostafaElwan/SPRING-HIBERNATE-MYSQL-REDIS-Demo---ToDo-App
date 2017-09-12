package com.elwan.todo.dao.redis;

import com.elwan.todo.common.ConfigManager;
import com.elwan.todo.dao.DAOFactory;
import com.elwan.todo.dao.TodoDAO;
import com.elwan.todo.dao.UserDAO;

public class RedisDAOFactory extends DAOFactory {
	
	public UserDAO getUserDAO() {
		return ConfigManager.getInstance().getBean(RedisUserDAOImpl.class);
	}
	
	public TodoDAO getTodoDAO() {
		return ConfigManager.getInstance().getBean(RedisTodoDAOImpl.class);
	}
	
}
