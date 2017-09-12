package com.elwan.todo.common;

import java.io.FileInputStream;
import java.util.Properties;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <h1>ConfigManager</h1> This class represents the manager of the configuration files 
 * which are needed to be used in the application. 
 * 
 * @author Mostafa Elwan
 * @version 1.0
 * @since 2017-08-04
 */
public class ConfigManager {
	
	/**
	 * Represents an instance of the current class. This instance 
	 * is used to manage the singleton pattern for this class. 
	 */
	private static ConfigManager INSTANT;
	
	/**
	 * This is the logger which is used to log the behavior of the class
	 */
	private static AppLogger logger = new AppLogger(ConfigManager.class);
	
	/**
	 * This is the holder which stores the properties state.
	 */
	private Properties prop = new Properties();
	
	private AnnotationConfigApplicationContext ctxt;

	/**
	 * Constructs an instance with states initialized with its default values.
	 * <b>Note:</b> This method is private to make sure that no other thread 
	 * calls it, and enforce the singleton pattern for this class. 
	 */
	private ConfigManager(){}
	
	/**
	 * @return Returns an instance of this class to make sure that only one instance is being created.
	 */
	public static ConfigManager getInstance() {
		if(INSTANT == null) {
			synchronized(ConfigManager.class) {
				if(INSTANT == null)
					INSTANT = new ConfigManager();
			}
		}
		
		return INSTANT;
	}

	/**
	 * Loads the properties of the determined configuration files.
	 * 
	 * @param configFilePath The path of the config file.
	 */
	public void loadProperties(String configFilePath) {
		try {
			prop.load(new FileInputStream(configFilePath));
			logger.debug("File [{}] has been read successfully.", configFilePath);
		} catch (Exception e) {
			logger.error("Error in loading properties file [{}] due to exception:", configFilePath, e);
		}
	}
	
	/**
	 * Returns the value of a specific property.
	 * 
	 * @param key The name of the property that needed to be retrieved.
	 * @return	The value of the specified property.
	 */
	public String get(String key) {
		return prop.getProperty(key);
	}
	
	public int getInt(String key) {
		return Integer.parseInt(prop.getProperty(key));
	}

	public void initializeSpring() {
		ctxt = new AnnotationConfigApplicationContext(AppConfig.class);
	}
	
	public <T>T getBean(Class<T> clazz) {
		return ctxt.getBean(clazz);
	}
}