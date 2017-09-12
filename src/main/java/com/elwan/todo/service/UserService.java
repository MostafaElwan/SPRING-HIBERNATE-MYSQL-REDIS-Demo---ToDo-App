package com.elwan.todo.service;

import java.util.List;

import com.elwan.todo.exception.APIException;
import com.elwan.todo.model.User;

public interface UserService {
	
	User login(String username, String password) throws APIException;
	
	void register(User user) throws APIException;
	
	void logout() throws APIException;

	List<User> getAll() throws APIException;

}
