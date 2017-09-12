package com.elwan.todo.dao;

import java.util.List;

import com.elwan.todo.exception.APIException;

public interface DAO<T> {
	
	List<T> all()throws APIException;
	
	T get(long id) throws APIException;
	
	long create(T t) throws APIException;
	
	void update(T t) throws APIException;
	
	void delete(long id) throws APIException;

}
