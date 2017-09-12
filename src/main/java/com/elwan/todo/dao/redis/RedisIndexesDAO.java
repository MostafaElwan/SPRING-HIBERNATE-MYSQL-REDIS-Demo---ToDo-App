package com.elwan.todo.dao.redis;

import java.util.concurrent.atomic.AtomicLong;

public class RedisIndexesDAO extends AbstractRedisDAO {

	private AtomicLong usersIndex = new AtomicLong(0);
	
	private AtomicLong todosIndex = new AtomicLong(0);
	
	public long getUserId() {
		return usersIndex.incrementAndGet();
	}
	
	public long getTodoId() {
		return todosIndex.incrementAndGet();
	}

}
