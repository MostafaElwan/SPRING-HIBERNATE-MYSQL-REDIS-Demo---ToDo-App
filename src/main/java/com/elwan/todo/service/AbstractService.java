package com.elwan.todo.service;

import com.elwan.todo.common.AppLogger;

public abstract class AbstractService {
	
	protected AppLogger logger;
	
	public void setLogger(Class clazz) {
		this.logger = new AppLogger(clazz);
	}

}
