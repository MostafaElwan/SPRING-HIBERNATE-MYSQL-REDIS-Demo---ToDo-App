package com.elwan.todo.dao.redis;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

import com.elwan.todo.dao.TodoDAO;
import com.elwan.todo.exception.APIException;
import com.elwan.todo.model.Todo;
import com.elwan.todo.model.User;

@Component
public class RedisTodoDAOImpl extends AbstractRedisDAO implements TodoDAO {

	@Override
	public List<Todo> all() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Todo get(long id) throws APIException {
		try {
			Todo todo = new Todo();
			Jedis j = getJedis();
			Map<String, String> props = j.hgetAll(String.format("TODO:%s", id));
			BeanUtilsBean.getInstance().populate(todo, props);
			return todo;
		} catch(Exception e) {
			throw new APIException("Error while creating TODO", e);
		}
	}

	@Override
	public long create(Todo t) throws APIException {
		try {
			long todoId = getTodoId();
			t.setId(todoId);
			Jedis j = getJedis();
			Map<String, String> props = BeanUtilsBean.getInstance().describe(t);
			j.hmset(String.format("TODO:%s", todoId), props);
			return todoId;
		} catch(Exception e) {
			throw new APIException("Error while creating TODO", e);
		}
	}

	@Override
	public void update(Todo t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Todo> all(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
