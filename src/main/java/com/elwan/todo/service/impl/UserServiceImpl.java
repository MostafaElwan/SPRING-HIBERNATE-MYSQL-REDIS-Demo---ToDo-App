package com.elwan.todo.service.impl;

import java.util.List;

import com.elwan.todo.common.AppConstant;
import com.elwan.todo.common.ConfigManager;
import com.elwan.todo.dao.DAOFactory;
import com.elwan.todo.dao.UserDAO;
import com.elwan.todo.exception.APIException;
import com.elwan.todo.model.User;
import com.elwan.todo.service.AbstractService;
import com.elwan.todo.service.UserService;

public class UserServiceImpl extends AbstractService implements UserService {

	@Override
	public User login(String username, String password) throws APIException {
		DAOFactory factory = DAOFactory.getDAOFactory(ConfigManager.getInstance().getInt(AppConstant.Keys.DB_TYPE));
		UserDAO dao = factory.getUserDAO();
		User u = dao.get(username, password);
		return u;
	}

	@Override
	public void register(User user) throws APIException {
		DAOFactory factory = DAOFactory.getDAOFactory(ConfigManager.getInstance().getInt(AppConstant.Keys.DB_TYPE));
		UserDAO dao = factory.getUserDAO();
		long userId = dao.create(user);
		user.setId(userId);
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getAll() throws APIException {
		DAOFactory factory = DAOFactory.getDAOFactory(ConfigManager.getInstance().getInt(AppConstant.Keys.DB_TYPE));
		UserDAO dao = factory.getUserDAO();
		List<User> users = dao.all();
		return users;
	}

}
