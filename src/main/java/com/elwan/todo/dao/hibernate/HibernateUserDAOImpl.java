package com.elwan.todo.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.elwan.todo.common.AppUtil;
import com.elwan.todo.dao.UserDAO;
import com.elwan.todo.exception.APIException;
import com.elwan.todo.model.User;

@Component
public class HibernateUserDAOImpl extends AbstractHibernateDAO implements UserDAO {
	
	@Override
	public List<User> all() throws APIException {
		Session s = sessionFactory.openSession();
		try {
			List<User> us = s.createQuery("from User").list();
			
			AppUtil.throwExceptionIfNullOrEmpty(us, "No users found !");
			
			return us;
		} finally {
			s.close();
		}
	}

	@Override
	public User get(long id) throws APIException {
		Session s = sessionFactory.openSession();
		try {
			User u = (User) s.get(User.class, id);
			
			AppUtil.throwExceptionIfNull(u, "No user found with Id: " + id);
			
			return u;
		} finally {
			s.close();
		}
	}
	
	public User get(String username, String password) throws APIException {
		Session s = sessionFactory.openSession();
		try {
			Query q = s.createQuery("from User where username = :usnm and password = :pwd");
			q.setCacheable(true);
			q.setParameter("usnm", username);
			q.setParameter("pwd", password);
			List<User> users = q.list();
			
			AppUtil.throwExceptionIfNullOrEmpty(users, "No user found with user: " + username);
			
			return users.get(0);
		} finally {
			s.close();
		}
	}

	@Override
	public long create(final User u) throws APIException {
		Session s = sessionFactory.openSession();
		try {
			long userId = (Long) s.save(u);
			return userId;
		} catch(Exception e) {
			throw new APIException("Error while creating user: ", e);
		} finally {
			s.close();
		}
	}

	@Override
	public void update(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User u) {
		// TODO Auto-generated method stub
		
	}
	
}
