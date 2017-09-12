package com.elwan.todo.dao.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import com.elwan.todo.common.AppUtil;
import com.elwan.todo.dao.UserDAO;
import com.elwan.todo.exception.APIException;
import com.elwan.todo.model.User;

@Component
public class RedisUserDAOImpl extends AbstractRedisDAO implements UserDAO {

	@Override
	public List<User> all() throws APIException {
		try {
			List<User> uList = new ArrayList<User>();
			Jedis j = getJedis();
			Map<String, String> props = j.hgetAll("USER:*");
			BeanUtilsBean.getInstance().populate(uList, props);
			return uList;
		} catch(Exception e) {
			throw new APIException("Error while creating USER", e);
		}
	}

	@Override
	public User get(long id) throws APIException {
		try {
			User u = new User();
			Jedis j = getJedis();
			Map<String, String> props = j.hgetAll(String.format("USER:%s", id));
			BeanUtilsBean.getInstance().populate(u, props);
			return u;
		} catch(Exception e) {
			throw new APIException("Error while creating USER", e);
		}
	}

	@Override
	public long create(User u) throws APIException {
		try {
			long userId = getUserId();
			u.setId(userId);
			Jedis j = getJedis();
			Map<String, String> props = BeanUtilsBean.getInstance().describe(u);
			props.remove("todoList");
			Transaction t = j.multi();
			t.set(String.format("%s:%s", u.getUsername(), u.getPassword()), String.valueOf(userId));
			t.hmset(String.format("USER:%s", userId), props);
			t.exec();
			return userId;
		} catch(Exception e) {
			throw new APIException("Error while creating USER", e);
		}
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User get(String username, String password) throws APIException {
		try {
			User u = new User();
			Jedis j = getJedis();
			String userId = j.get(String.format("%s:%s", username, password));
			AppUtil.throwExceptionIfNull(userId, "Invlid username or password !");
			Map<String, String> props = j.hgetAll(String.format("USER:%s", userId));
			BeanUtilsBean.getInstance().populate(u, props);
			return u;
		} catch(Exception e) {
			throw new APIException("Error while creating USER", e);
		}
	}

}
