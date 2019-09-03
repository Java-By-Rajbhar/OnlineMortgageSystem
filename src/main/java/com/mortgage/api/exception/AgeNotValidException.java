package com.mortgage.api.exception;
/**
 * 
 * @author Sushil
 *
 */
public class AgeNotValidException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AgeNotValidException(String message)
	{
		super(message);
	}

}
