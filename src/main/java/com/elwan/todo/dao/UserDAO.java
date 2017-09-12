package com.elwan.todo.dao;

import com.elwan.todo.exception.APIException;
import com.elwan.todo.model.User;

public interface UserDAO extends DAO<User> {
	
	User get(String username, String password) throws APIException;

}
