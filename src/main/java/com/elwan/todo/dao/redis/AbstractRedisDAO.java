package com.elwan.todo.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class AbstractRedisDAO<T> {
	
	private JedisPool  jp;
	
	@Autowired
	public void setJedis(JedisPool jp) {
		this.jp = jp;
	}
	
	protected Jedis getJedis() {
		return jp.getResource();
	}
	
}
