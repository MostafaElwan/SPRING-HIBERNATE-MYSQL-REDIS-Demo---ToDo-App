package com.elwan.todo.dao.hibernate;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.elwan.todo.common.AppUtil;
import com.elwan.todo.dao.TodoDAO;
import com.elwan.todo.exception.APIException;
import com.elwan.todo.model.Todo;
import com.elwan.todo.model.User;

@Component
public class HibernateTodoDAOImpl extends AbstractHibernateDAO implements TodoDAO {

	@Override
	public List<Todo> all() throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Todo get(long id) throws APIException {
		Session s = sessionFactory.openSession();
		try {
			List<Todo> todoList = s.createCriteria(Todo.class)
					.add(Restrictions.eq("id", id))
					.list();
			
			AppUtil.throwExceptionIfNullOrEmpty(todoList, "No user found with Id: " + id);
			
			return todoList.get(0);
		} finally {
			s.close();
		}
	}

	@Override
	public long create(Todo t) throws APIException {
		Session s = sessionFactory.openSession();
		try {
			long todoId = (Long) s.save(t);
			if(t.getUser().getEmail().indexOf("5") != -1) {
				throw new APIException(String.format("Simulating transactional failure because mail is [%s]", t.getUser().getEmail()));
			}
			return todoId;
		} finally {
			s.close();
		}
	}

	@Override
	public void update(Todo t) {
		Session s = sessionFactory.openSession();
		try {
			s.update(t);
		} finally {
			s.close();
		}
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Todo> all(User user) {
		Session s = sessionFactory.openSession();
		try {
			User u = (User) s.get(User.class, user.getId());
			Hibernate.initialize(u.getTodoList());
			return u.getTodoList();
		} finally {
			s.close();
		}
		
	}
	
}
