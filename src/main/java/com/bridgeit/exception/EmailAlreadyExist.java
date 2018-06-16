package com.bridgeit.exception;

public class EmailAlreadyExist extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EmailAlreadyExist(String msg)
	{
		super("Email Already exist");
	}
	
	
}
