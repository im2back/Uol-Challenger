package com.im2back.github.registrationmicroservice.service.registration.exceptions;

public class ClientConnectionFailedException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ClientConnectionFailedException(String content) {
		super(content);
	}

}
