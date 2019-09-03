package com.mortgage.api.exception;

public class UserIdAlreadyExitsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserIdAlreadyExitsException(String message)
	{
		super(message);
	}

}
