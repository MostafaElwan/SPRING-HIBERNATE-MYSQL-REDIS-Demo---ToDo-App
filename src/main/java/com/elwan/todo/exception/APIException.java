package com.elwan.todo.exception;

/**
 * <h1>APIException</h1> This class signals the application that there is an error has been 
 * occurred. It wraps any other exception happened in the application, in addition to being 
 * initialized based on a business validation error.
 * 
 * 
 * @author Mostafa Elwan
 * @version 1.0
 * @since 2017-08-04
 */
public class APIException extends Exception {

	/**
	 * Constructs a new instance with states initialized into its default values.
	 */
	public APIException() {
		super();
	}
	
	/**
	 * Constructs a new instance with states initialized with a specific message.
	 * 
	 * @param message Represents a description of the error that causes this exception.
	 */
	public APIException(String message) {
		super(message);
	}
	
	/**
	 * Constructs a new instance with states initialized with a specific message and throwable.
	 * 
	 * @param message Represents a description of the error that causes this exception.
	 * @param throwable Represents the throwable that caused this exception.
	 */
	public APIException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
