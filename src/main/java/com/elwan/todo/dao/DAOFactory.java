package com.elwan.todo.dao;

import com.elwan.todo.common.AppConstant;
import com.elwan.todo.dao.hibernate.HibernateDAOFactory;
import com.elwan.todo.dao.mysql.MysqlDAOFactory;
import com.elwan.todo.dao.redis.RedisDAOFactory;
import com.elwan.todo.exception.APIException;


public abstract class DAOFactory {
	
	public abstract UserDAO getUserDAO();
	
	public abstract TodoDAO getTodoDAO();
	
	public static DAOFactory getDAOFactory(int type) throws APIException {
		switch (type) {
			case AppConstant.DBType.MYSQL:
				return new MysqlDAOFactory();
			case AppConstant.DBType.REDIS:
				return new RedisDAOFactory();
			case AppConstant.DBType.HIBERNATE:
				return new HibernateDAOFactory();
		}
		
		throw new APIException("Invalid DAO Factory type !");
	}

}
