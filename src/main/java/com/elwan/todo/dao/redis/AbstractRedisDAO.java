package com.elwan.todo.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.elwan.todo.common.ConfigManager;

public class AbstractRedisDAO<T> {
	
	private JedisPool  jp;
	
	@Autowired
	public void setJedis(JedisPool jp) {
		this.jp = jp;
	}
	
	protected Jedis getJedis() {
		return jp.getResource();
	}
	
	protected long getUserId() {
		return ConfigManager.getInstance().getBean(RedisIndexesDAO.class).getUserId();
	}
	
	protected long getTodoId() {
		return ConfigManager.getInstance().getBean(RedisIndexesDAO.class).getTodoId();
	}
	
}
