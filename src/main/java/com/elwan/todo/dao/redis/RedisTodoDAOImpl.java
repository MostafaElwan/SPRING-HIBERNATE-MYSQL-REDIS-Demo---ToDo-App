package com.elwan.todo.dao.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import com.elwan.todo.common.AppConstant;
import com.elwan.todo.dao.TodoDAO;
import com.elwan.todo.exception.APIException;
import com.elwan.todo.model.Todo;
import com.elwan.todo.model.User;

@Component
public class RedisTodoDAOImpl extends AbstractRedisDAO<Todo> implements TodoDAO {

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
			throw new APIException(String.format("Error while fetching TODO [%s]", id), e);
		}
	}

	@Override
	public long create(Todo todo) throws APIException {
		try {
			Jedis j = getJedis();
			long todoId = j.incr(AppConstant.Redis.Keys.TODO_ID);
			todo.setId(todoId);
			
			Transaction t = j.multi();
			Map<String, String> props = BeanUtilsBean.getInstance().describe(todo);
			t.hmset(String.format("TODO:%s", todoId), props);
			t.sadd(String.format("USER-TODO-LIST:%s", todo.getUser().getId()), String.valueOf(todo.getId()));
			t.exec();
			return todoId;
		} catch(Exception e) {
			throw new APIException(String.format("Error while creating TODO [%s]", todo.getTitle()), e);
		}
	}

	@Override
	public void update(Todo t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Todo todo) throws APIException {
		try {
			Jedis j = getJedis();
			
			Transaction t = j.multi();
			t.srem(String.format("USER-TODO-LIST:%s", todo.getUser().getId()), String.valueOf(todo.getId()));
			t.del(String.format("TODO:%s", todo.getId()));
			t.exec();
		} catch(Exception e) {
			throw new APIException(String.format("Error while creating TODO [%s]", todo.getTitle()), e);
		}
	}

	@Override
	public List<Todo> all(User user) throws APIException {
		List<Todo> todoList = new ArrayList<Todo>();
		try {
			Todo todo = null;
			Jedis j = getJedis();
			Set<String> todoIds = j.smembers(String.format("USER-TODO-LIST:%s", user.getId()));
			for(String todoId : todoIds) {
				todo = new Todo();
				Map<String, String> props = j.hgetAll(String.format("TODO:%s", todoId));
				props.remove("user");
				BeanUtilsBean.getInstance().populate(todo, props);
				todoList.add(todo);
			}
			return todoList;
		} catch(Exception e) {
			throw new APIException(String.format("Error while fetching todo list of user [%s]", user.getId()), e);
		}
	}

}
