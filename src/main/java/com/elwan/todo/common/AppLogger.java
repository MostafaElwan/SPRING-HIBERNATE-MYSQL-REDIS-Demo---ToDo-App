package com.elwan.todo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogger {
	
	private Logger logger;
	
	public AppLogger(Class clazz) {
		logger = LoggerFactory.getLogger(clazz);
	}
	
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}
	
	public void debug(String message, Object...parameters) {
		if(isDebugEnabled())
			logger.debug(message, parameters);
	}
	
	public void info(String message, Object...parameters) {
		logger.info(message, parameters);
	}
	
	public void warn(String message, Object...parameters) {
		logger.warn(message, parameters);
	}
	
	public void error(String message) {
		error(message, new Object[]{});
	}
	
	public void error(String message, Object...parameters) {
		logger.error(message, parameters);
	}
	
	public void error(String message, Throwable throwable) {
		logger.error(message, throwable);
	}
	
}
